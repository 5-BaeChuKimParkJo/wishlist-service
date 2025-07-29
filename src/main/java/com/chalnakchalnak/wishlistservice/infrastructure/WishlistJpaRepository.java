package com.chalnakchalnak.wishlistservice.infrastructure;

import com.chalnakchalnak.wishlistservice.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistJpaRepository extends JpaRepository<Wishlist, Long> {

    boolean existsByMemberUuidAndPostUuid(String memberUuid, String postUuid);
    Optional<Wishlist> findByMemberUuidAndPostUuid(String memberUuid, String postUuid);
    List<Wishlist> findAllByMemberUuidOrderByCreatedAtDesc(String memberUuid);
    long countByMemberUuid(String memberUuid);
}
