package com.chalnakchalnak.wishlistservice.infrastructure;

import com.chalnakchalnak.wishlistservice.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistJpaRepository extends JpaRepository<Wishlist, Long> {

    boolean existsByMemberUuidAndPostUuid(String memberUuid, String postUuid);

}
