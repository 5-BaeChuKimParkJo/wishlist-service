package com.chalnakchalnak.wishlistservice.common.exception;

import com.chalnakchalnak.wishlistservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatusCode;

public record ExceptionResponseEntity<T>(@JsonIgnore HttpStatusCode httpStatus, int code, String message) {

    public ExceptionResponseEntity(BaseResponseStatus status) {
        this(status.getHttpStatusCode(), status.getCode(), status.getMessage());
    }

    public ExceptionResponseEntity(BaseResponseStatus status, String message) {
        this(status.getHttpStatusCode(), status.getCode(), message);
    }
}
