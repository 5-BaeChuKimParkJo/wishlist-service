package com.chalnakchalnak.wishlistservice.domain;

import com.chalnakchalnak.wishlistservice.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "member_wishlist",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_member_post", columnNames = {"member_uuid", "post_uuid"})
        },
        indexes = {
        @Index(name = "idx_member_uuid", columnList = "member_uuid"),
        @Index(name = "idx_member_post_check", columnList = "member_uuid, post_uuid")
}

)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Wishlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "member_uuid", nullable = false, updatable = false, length = 40)
    private String memberUuid;

    @Column(name = "post_uuid", nullable = false, updatable = false, length = 40)
    private String postUuid;

    @Builder
    public Wishlist(Long id, String memberUuid, String postUuid) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
    }
}
