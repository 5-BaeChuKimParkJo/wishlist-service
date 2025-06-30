package com.chalnakchalnak.wishlistservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 5000 : chat-service 에러
     */

    /**
     * 5000~5099 : security 에러
     */
    INVALID_USER_UUID(HttpStatus.UNAUTHORIZED, 5000, "유효하지 않은 사용자 UUID입니다."),
    NO_CHAT_ACCESS(HttpStatus.FORBIDDEN, 5001, "채팅방에 접근할 수 없습니다. 권한을 확인해주세요."),

    /**
     * 5100~5199: Request 유효성 에러
     */
    BAD_REQUEST_INVALID_PARAM(HttpStatus.BAD_REQUEST, 5100, "잘못된 요청입니다. 파라미터를 확인해주세요."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 5101, "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 5102, "허용되지 않은 HTTP 메서드입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 5103, "유효하지 입력입니다"),
    INVALID_OBJECT_ID(HttpStatus.BAD_REQUEST, 5104, "유효하지 않은 ObjectId입니다."),

    /**
     * 5200~5299 : 채팅 에러
     */
    FAILED_PUBLISH_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, 5200, "메시지 전송에 실패하였습니다."),
    FAILED_CONSUME_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, 5201, "메시지 수신에 실패하였습니다."),
    FAILED_MESSAGE_PROCESSING(HttpStatus.INTERNAL_SERVER_ERROR, 5202, "메시지 토픽 수신 후 내부 처리에 실패하였습니다."),
    FAILED_PUBLISH_CHAT_ROOM_SUMMARY_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, 5203, "채팅방 요약 업데이트 이벤트 발행에 실패하였습니다."),
    FAILED_UPDATE_READ_CHECK_POINT(HttpStatus.INTERNAL_SERVER_ERROR, 5204, "읽음 체크포인트 업데이트에 실패하였습니다."),

    /**
     * 5300~5399 : PreSignedUrl 관련 에러
     */
    UNABLE_TO_CALCULATE_HMAC(HttpStatus.INTERNAL_SERVER_ERROR, 2003, "HMAC을 계산할 수 없습니다"),
    INVALID_IMAGE_MESSAGE_KEY_FORMAT(HttpStatus.BAD_REQUEST, 5300, "이미지 메시지 키 형식이 유효하지 않습니다."),
    INVALID_IMAGE_MESSAGE_SENDER(HttpStatus.BAD_REQUEST, 5301, "이미지 메시지의 발신자 UUID가 키 내에 포함된 UUID와 일치하지 않습니다."),
    IMAGE_FILE_NOT_FOUND_IN_S3(HttpStatus.NOT_FOUND, 5302, "S3에서 이미지 파일을 찾을 수 없습니다."),
    FAILED_TO_ACCESS_S3(HttpStatus.INTERNAL_SERVER_ERROR, 5303, "S3에 접근하는 데 실패했습니다."),

    /**
     * 5900~5999 : 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5900,"서버 내부 오류가 발생했습니다. 관리자에게 문의해주세요."),
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, 5901, "채팅방을 찾을 수 없습니다."),
    CHAT_ROOM_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 5902, "해당 채팅방에 포함되지 않은 유저입니다."),
    FAILED_SERIALIZE_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, 5903, "메시지 직렬화에 실패했습니다."),
    INVALID_MESSAGE_SEQ(HttpStatus.BAD_REQUEST, 5904, "유효하지 않은 메시지 시퀀스입니다."),
    MEMBER_CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, 5905, "해당 유저의 채팅방을 찾을 수 없습니다."),
    CHAT_ROOM_MEMBER_EXIT_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 5906, "채팅방 멤버 퇴장 기록을 찾을 수 없습니다."),
    CHAT_MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, 5907, "해당 채팅 메시지를 찾을 수 없습니다."),;



    private final HttpStatusCode httpStatusCode;
    private final int code;
    private final String message;

}
