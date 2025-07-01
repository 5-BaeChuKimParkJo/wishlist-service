package com.chalnakchalnak.wishlistservice.dto.in;

import com.chalnakchalnak.wishlistservice.domain.Wishlist;
import com.chalnakchalnak.wishlistservice.vo.in.AddWishlistRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddWishlistRequestDto {

    private String memberUuid;
    private String postUuid;

    @Builder
    public AddWishlistRequestDto(String memberUuid, String postUuid) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
    }

    public static AddWishlistRequestDto from(AddWishlistRequestVo addWishlistRequestVo, String memberUuid) {
        return AddWishlistRequestDto.builder()
                .memberUuid(memberUuid)
                .postUuid(addWishlistRequestVo.getPostUuid())
                .build();
    }

    public Wishlist toEntity() {
        return Wishlist.builder()
                .memberUuid(memberUuid)
                .postUuid(postUuid)
                .build();
    }
}
