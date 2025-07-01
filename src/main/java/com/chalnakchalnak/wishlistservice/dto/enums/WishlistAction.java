package com.chalnakchalnak.wishlistservice.dto.enums;

import com.chalnakchalnak.wishlistservice.common.exception.BaseException;
import com.chalnakchalnak.wishlistservice.common.response.BaseResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WishlistAction {

    ADD("ADD", "등록"),
    REMOVE("REMOVE", "해제");

    private final String action;
    private final String label;

    public static WishlistAction fromString(String action) {
        for (WishlistAction wishlistAction : WishlistAction.values()) {
            if (wishlistAction.getAction().equalsIgnoreCase(action)) {
                return wishlistAction;
            }
        }
        throw new BaseException(BaseResponseStatus.INVALID_WISHLIST_ACTION);
    }
}
