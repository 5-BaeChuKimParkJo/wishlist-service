package com.chalnakchalnak.wishlistservice.dto;

import com.chalnakchalnak.wishlistservice.dto.enums.WishlistAction;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.RemoveWishlistRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WishlistEventDto {
    private String memberUuid;
    private String postUuid;
    private WishlistAction action;

    @Builder
    public WishlistEventDto(String memberUuid, String postUuid, WishlistAction action) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
        this.action = action;
    }

    public static WishlistEventDto fromAddRequest(AddWishlistRequestDto addWishlistRequestDto) {
        return WishlistEventDto.builder()
                .memberUuid(addWishlistRequestDto.getMemberUuid())
                .postUuid(addWishlistRequestDto.getPostUuid())
                .action(WishlistAction.ADD)
                .build();
    }

    public static WishlistEventDto fromRemoveRequest(RemoveWishlistRequestDto removeWishlistRequestDto) {
        return WishlistEventDto.builder()
                .memberUuid(removeWishlistRequestDto.getMemberUuid())
                .postUuid(removeWishlistRequestDto.getPostUuid())
                .action(WishlistAction.REMOVE)
                .build();
    }
}
