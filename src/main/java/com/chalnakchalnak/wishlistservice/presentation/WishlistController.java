package com.chalnakchalnak.wishlistservice.presentation;

import com.chalnakchalnak.wishlistservice.application.WishlistService;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.CheckPostInWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.RemoveWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.vo.in.AddWishlistRequestVo;
import com.chalnakchalnak.wishlistservice.vo.in.CheckPostInWishlistRequestVo;
import com.chalnakchalnak.wishlistservice.vo.in.GetWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.vo.in.RemoveWishlistRequestVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public void addWishlist(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestBody @Valid AddWishlistRequestVo addWishlistRequestVo
    ) {
            wishlistService.addWishlist(AddWishlistRequestDto.from(addWishlistRequestVo, memberUuid));
    }

    @DeleteMapping
    public void removeWishlist(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @RequestBody @Valid RemoveWishlistRequestVo removeWishlistRequestVo
    ) {
        wishlistService.removeWishlist(RemoveWishlistRequestDto.from(removeWishlistRequestVo, memberUuid));
    }

    @GetMapping("/check")
    public boolean isInWishlist(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @ModelAttribute @Valid CheckPostInWishlistRequestVo checkPostInWishlistRequestVo
            ) {
        return wishlistService.checkedWishlist(CheckPostInWishlistRequestDto.from(checkPostInWishlistRequestVo, memberUuid));
    }

    @GetMapping
    public List<String> getWishlist(
            @RequestHeader("X-Member-Uuid") String memberUuid
    ) {
        return wishlistService.getWishlist(GetWishlistRequestDto.from(memberUuid)).stream().toList();
    }

}
