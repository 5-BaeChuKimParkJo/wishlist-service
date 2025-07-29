package com.chalnakchalnak.wishlistservice.dto.in;

import com.chalnakchalnak.wishlistservice.vo.in.CheckPostInWishlistRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckPostInWishlistRequestDto {

    private String memberUuid;
    private String postUuid;

    @Builder
    public CheckPostInWishlistRequestDto(String memberUuid, String postUuid) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
    }

    public static CheckPostInWishlistRequestDto from(CheckPostInWishlistRequestVo checkPostInWishlistRequestVo, String memberUuid) {
        return CheckPostInWishlistRequestDto.builder()
                .memberUuid(memberUuid)
                .postUuid(checkPostInWishlistRequestVo.getPostUuid())
                .build();
    }
}
