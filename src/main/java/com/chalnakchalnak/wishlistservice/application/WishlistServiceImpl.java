package com.chalnakchalnak.wishlistservice.application;

import com.chalnakchalnak.wishlistservice.common.exception.BaseException;
import com.chalnakchalnak.wishlistservice.common.response.BaseResponseStatus;
import com.chalnakchalnak.wishlistservice.domain.Wishlist;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.CheckPostInWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.RemoveWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.infrastructure.WishlistJpaRepository;
import com.chalnakchalnak.wishlistservice.infrastructure.WishlistRedisTemplate;
import com.chalnakchalnak.wishlistservice.dto.in.GetWishlistRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private static final int MAX_WISHLIST_COUNT = 100;

    private final WishlistJpaRepository wishlistJpaRepository;
    private final WishlistRedisTemplate wishlistRedisTemplate;
    private final WishlistEventProducer wishlistEventProducer;

    @Transactional
    @Override
    public void addWishlist(AddWishlistRequestDto addWishlistRequestDto) {
        final String memberUuid = addWishlistRequestDto.getMemberUuid();
        final String postUuid = addWishlistRequestDto.getPostUuid();

        if (!wishlistRedisTemplate.hasKey(memberUuid)) {
            syncWishlistFromDbToRedis(memberUuid);
        }

        Long currentSize = wishlistRedisTemplate.getCount(memberUuid);
        if (currentSize == null) {
            currentSize = wishlistJpaRepository.countByMemberUuid(memberUuid);
        }
        if (currentSize >= MAX_WISHLIST_COUNT) {
            throw new BaseException(BaseResponseStatus.WISHLIST_LIMIT_EXCEEDED);
        }

        if (wishlistRedisTemplate.checkedWishlist(memberUuid, postUuid) ||
                wishlistJpaRepository.existsByMemberUuidAndPostUuid(memberUuid, postUuid)) {
            throw new BaseException(BaseResponseStatus.ALREADY_WISHLISTED);
        }

        wishlistJpaRepository.save(addWishlistRequestDto.toEntity());
        wishlistRedisTemplate.addToWishlist(memberUuid, postUuid);
        wishlistEventProducer.publishAddWishlistEvent(addWishlistRequestDto);
    }

    @Transactional
    @Override
    public void removeWishlist(RemoveWishlistRequestDto removeWishlistRequestDto) {
        final String memberUuid = removeWishlistRequestDto.getMemberUuid();
        final String postUuid = removeWishlistRequestDto.getPostUuid();

        final Wishlist wishlist = wishlistJpaRepository.findByMemberUuidAndPostUuid(memberUuid, postUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_IN_WISHLIST));

        wishlistJpaRepository.delete(wishlist);
        wishlistRedisTemplate.removeFromWishlist(memberUuid, postUuid);
        wishlistEventProducer.publishRemoveWishlistEvent(removeWishlistRequestDto);
    }

    @Override
    public boolean checkedWishlist(CheckPostInWishlistRequestDto checkPostInWishlistRequestDto) {
        final String memberUuid = checkPostInWishlistRequestDto.getMemberUuid();
        final String postUuid = checkPostInWishlistRequestDto.getPostUuid();

        if (!wishlistRedisTemplate.hasKey(memberUuid)) {
            syncWishlistFromDbToRedis(memberUuid);
        }

        if (wishlistRedisTemplate.checkedWishlist(memberUuid, postUuid)) {
            return true;
        }

        final boolean checked = wishlistJpaRepository.existsByMemberUuidAndPostUuid(memberUuid, postUuid);
        if (checked) {
            wishlistRedisTemplate.addToWishlist(memberUuid, postUuid);
        }
        return checked;
    }

    @Override
    public List<String> getWishlist(GetWishlistRequestDto getWishlistRequestDto) {
        final String memberUuid = getWishlistRequestDto.getMemberUuid();
        final List<String> cached = wishlistRedisTemplate.getWishlist(memberUuid);

        if (!cached.isEmpty()) {
            return cached;
        }
        return syncWishlistFromDbToRedis(memberUuid);
    }

    /**
     * DB 기준으로 Redis를 완전히 초기화하고 최신순 List 반환
     */
    private List<String> syncWishlistFromDbToRedis(String memberUuid) {
        final List<Wishlist> wishlist = wishlistJpaRepository.findAllByMemberUuidOrderByCreatedAtDesc(memberUuid);
        if (wishlist.isEmpty()) {
            return Collections.emptyList();
        }

        final List<String> postUuids = wishlist.stream()
                .map(Wishlist::getPostUuid)
                .toList();

        wishlistRedisTemplate.syncFromDb(memberUuid, postUuids);
        return postUuids;
    }
}
