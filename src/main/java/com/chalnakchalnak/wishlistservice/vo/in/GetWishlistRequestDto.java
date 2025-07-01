package com.chalnakchalnak.wishlistservice.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetWishlistRequestDto {

    private String memberUuid;

    @Builder
    public GetWishlistRequestDto(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public static GetWishlistRequestDto from(String memberUuid) {
        return GetWishlistRequestDto.builder()
                .memberUuid(memberUuid)
                .build();
    }
}
