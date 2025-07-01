package com.chalnakchalnak.wishlistservice.dto;

import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class WishlistEventDto {

    private String memberUuid;
    private String postUuid;
    private String action; // "ADD" or "REMOVE"

    @Builder
    public WishlistEventDto(String memberUuid, String postUuid, String action) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
        this.action = action;
    }

    public static WishlistEventDto fromAddRequest(AddWishlistRequestDto addWishlistRequestDto) {
        return WishlistEventDto.builder()
                .memberUuid(addWishlistRequestDto.getMemberUuid())
                .postUuid(addWishlistRequestDto.getPostUuid())
                .action("ADD")
                .build();
    }

    public static WishlistEventDto fromRemoveRequest(String memberUuid, String postUuid) {
        return WishlistEventDto.builder()
                .memberUuid(memberUuid)
                .postUuid(postUuid)
                .action("REMOVE")
                .build();
    }
}
