package com.chalnakchalnak.wishlistservice.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class WishlistRedisTemplate {

    private final RedisTemplate<String, String> redisTemplate;

    private static final int MAX_WISHLIST_COUNT = 100;

    public boolean hasKey(String memberUuid) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getRedisKey(memberUuid)));
    }

    public Long getCount(String memberUuid) {
        return redisTemplate.opsForZSet().zCard(getRedisKey(memberUuid));
    }

    public boolean checkedWishlist(String memberUuid, String postUuid) {
        return redisTemplate.opsForZSet().score(getRedisKey(memberUuid), postUuid) != null;
    }

    public void addToWishlist(String memberUuid, String postUuid) {
        redisTemplate.opsForZSet().add(getRedisKey(memberUuid), postUuid, System.currentTimeMillis());
    }

    public void removeFromWishlist(String memberUuid, String postUuid) {
        redisTemplate.opsForZSet().remove(getRedisKey(memberUuid), postUuid);
    }

    public List<String> getWishlist(String memberUuid) {
        final Set<String> cached = redisTemplate.opsForZSet().reverseRange(getRedisKey(memberUuid), 0, MAX_WISHLIST_COUNT - 1);
        if (cached == null || cached.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(cached);
    }

    public void syncFromDb(String memberUuid, List<String> postUuids) {
        final String redisKey = getRedisKey(memberUuid);
        Long score = System.currentTimeMillis();
        for (String postUuid : postUuids) {
            redisTemplate.opsForZSet().add(redisKey, postUuid, score--);
        }
    }

    private String getRedisKey(String memberUuid) {
        return "wishlist:member:" + memberUuid;
    }
}
