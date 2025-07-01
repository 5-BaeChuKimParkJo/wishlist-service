package com.chalnakchalnak.wishlistservice.presentation;

import com.chalnakchalnak.wishlistservice.application.WishlistService;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.vo.in.AddWishlistRequestVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

}
