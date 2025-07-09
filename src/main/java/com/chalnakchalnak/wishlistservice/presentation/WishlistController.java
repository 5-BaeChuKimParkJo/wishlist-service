package com.chalnakchalnak.wishlistservice.presentation;

import com.chalnakchalnak.wishlistservice.application.WishlistService;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.CheckPostInWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.RemoveWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.vo.in.AddWishlistRequestVo;
import com.chalnakchalnak.wishlistservice.vo.in.CheckPostInWishlistRequestVo;
import com.chalnakchalnak.wishlistservice.dto.in.GetWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.vo.in.RemoveWishlistRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @Operation(summary = "Add Wishlist API", description = "게시글 찜 등록 API", tags = {"wishlist"})
    @PostMapping
    public void addWishlist(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestBody @Valid AddWishlistRequestVo addWishlistRequestVo
    ) {
            wishlistService.addWishlist(AddWishlistRequestDto.from(addWishlistRequestVo, memberUuid));
    }

    @Operation(summary = "Delete Wishlist API", description = "게시글 찜 등록 해제 API", tags = {"wishlist"})
    @DeleteMapping
    public void removeWishlist(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @RequestBody @Valid RemoveWishlistRequestVo removeWishlistRequestVo
    ) {
        wishlistService.removeWishlist(RemoveWishlistRequestDto.from(removeWishlistRequestVo, memberUuid));
    }

    @Operation(summary = "Check Wishlist API", description = "게시글 찜 여부 확안 API", tags = {"wishlist"})
    @GetMapping("/check")
    public boolean isInWishlist(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @ModelAttribute @Valid CheckPostInWishlistRequestVo checkPostInWishlistRequestVo
            ) {
        return wishlistService.checkedWishlist(CheckPostInWishlistRequestDto.from(checkPostInWishlistRequestVo, memberUuid));
    }

    @Operation(summary = "Get Wishlist API", description = "게시글 찜 리스트 조회 API", tags = {"wishlist"})
    @GetMapping
    public List<String> getWishlist(
            @RequestHeader("X-Member-Uuid") String memberUuid
    ) {
        return wishlistService.getWishlist(GetWishlistRequestDto.from(memberUuid)).stream().toList();
    }

}
