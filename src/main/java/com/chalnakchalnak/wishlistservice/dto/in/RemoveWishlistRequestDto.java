package com.chalnakchalnak.wishlistservice.dto.in;

import com.chalnakchalnak.wishlistservice.vo.in.RemoveWishlistRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveWishlistRequestDto {

    private String memberUuid;
    private String postUuid;

    @Builder
    public RemoveWishlistRequestDto(String memberUuid, String postUuid) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
    }

    public static RemoveWishlistRequestDto from(RemoveWishlistRequestVo removeWishlistRequestVo, String memberUuid) {
        return RemoveWishlistRequestDto.builder()
                .memberUuid(memberUuid)
                .postUuid(removeWishlistRequestVo.getPostUuid())
                .build();
    }
}
