package com.chalnakchalnak.wishlistservice.common.exception;

import com.chalnakchalnak.wishlistservice.common.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final BaseResponseStatus status;
    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}