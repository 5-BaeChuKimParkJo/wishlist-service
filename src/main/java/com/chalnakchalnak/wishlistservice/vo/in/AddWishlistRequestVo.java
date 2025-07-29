package com.chalnakchalnak.wishlistservice.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.UUID;

@Getter
public class AddWishlistRequestVo {

    @NotBlank(message = "postUuid는 필수 값입니다.")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "postUuid는 UUID 형식이어야 합니다."
    )
    private String postUuid;
}
