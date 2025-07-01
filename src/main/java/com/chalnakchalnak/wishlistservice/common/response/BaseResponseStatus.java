package com.chalnakchalnak.wishlistservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 3000~3999 : chat-service 에러
     */

    /**
     * 3000~3099 :
     */

    /**
     * 3100~3199: Request 유효성 에러
     */
    BAD_REQUEST_INVALID_PARAM(HttpStatus.BAD_REQUEST, 5100, "잘못된 요청입니다. 파라미터를 확인해주세요."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 5101, "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 5102, "허용되지 않은 HTTP 메서드입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 5103, "유효하지 입력입니다"),

    /**
     * 3200~3299 : 찜 등록/해제 관련 에러
     */
    WISHLIST_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, 5200, "찜은 최대 100개까지 등록할 수 있습니다."),
    ALREADY_WISHLISTED(HttpStatus.BAD_REQUEST, 5201, "이미 찜한 상품입니다."),
    NOT_IN_WISHLIST(HttpStatus.BAD_REQUEST, 5202, "찜 목록에 없는 상품입니다."),

    /**
     * 3300~3399 : kafka producer 에러
     */
    FAILED_SERIALIZE_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, 5300, "메시지 직렬화에 실패했습니다."),

    /**
     * 3900~3999 : 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5900,"서버 내부 오류가 발생했습니다. 관리자에게 문의해주세요."),;



    private final HttpStatusCode httpStatusCode;
    private final int code;
    private final String message;

}
