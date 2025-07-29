package com.chalnakchalnak.wishlistservice.application;

import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.CheckPostInWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.RemoveWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.GetWishlistRequestDto;

import java.util.List;

public interface WishlistService {

    void addWishlist(AddWishlistRequestDto addWishlistRequestDto);
    void removeWishlist(RemoveWishlistRequestDto removeWishlistRequestDto);
    boolean checkedWishlist(CheckPostInWishlistRequestDto checkPostInWishlistRequestDto);
    List<String> getWishlist(GetWishlistRequestDto getWishlistRequestDto);
}
