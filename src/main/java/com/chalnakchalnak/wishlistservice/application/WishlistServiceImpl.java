package com.chalnakchalnak.wishlistservice.application;

import com.chalnakchalnak.wishlistservice.common.exception.BaseException;
import com.chalnakchalnak.wishlistservice.common.response.BaseResponseStatus;
import com.chalnakchalnak.wishlistservice.domain.Wishlist;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.infrastructure.WishlistJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private static final int MAX_WISHLIST_COUNT = 100;

    private final WishlistJpaRepository wishlistJpaRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final WishlistEventProducer wishlistEventProducer;

    @Transactional
    @Override
    public void addWishlist(AddWishlistRequestDto addWishlistRequestDto) {
        final String memberUuid = addWishlistRequestDto.getMemberUuid();
        final String postUuid = addWishlistRequestDto.getPostUuid();

        String redisKey = getRedisKey(memberUuid);

        // 최대 100개 제한
        Long currentSize = redisTemplate.opsForSet().size(redisKey);
        if (currentSize == null || currentSize == 0) {
            currentSize = wishlistJpaRepository.countByMemberUuid(memberUuid);
        }
        if (currentSize >= MAX_WISHLIST_COUNT) {
            throw new BaseException(BaseResponseStatus.WISHLIST_LIMIT_EXCEEDED);
        }

        // 중복 체크
        if (redisTemplate.opsForSet().isMember(redisKey, postUuid) ||
                wishlistJpaRepository.existsByMemberUuidAndPostUuid(memberUuid, postUuid)) {
            throw new BaseException(BaseResponseStatus.ALREADY_WISHLISTED);
        }

        wishlistJpaRepository.save(addWishlistRequestDto.toEntity());
        redisTemplate.opsForSet().add(redisKey, postUuid);

        wishlistEventProducer.publishAddWishlistEvent(addWishlistRequestDto);
    }


    private String getRedisKey(String memberUuid) {
        StringBuilder key = new StringBuilder();
        key.append("wishlist:member:")
                .append(memberUuid);

        final String result = key.toString();

        return result;
    }
}
