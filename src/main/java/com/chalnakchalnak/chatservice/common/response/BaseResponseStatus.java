package com.chalnakchalnak.chatservice.common.response;

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

    /**
     * 5200~5299 : 채팅 에러
     */
    FAILED_CONSUME_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, 5200, "메시지 수신 실패"),

    /**
     * 5900~5999 : 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5900,"서버 내부 오류가 발생했습니다. 관리자에게 문의해주세요."),
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, 5901, "채팅방을 찾을 수 없습니다."),
    CHAT_ROOM_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 5902, "해당 채팅방에 포함되지 않은 유저입니다."),
    FAILED_SERIALIZE_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, 5903, "메시지 직렬화에 실패했습니다."),;



    private final HttpStatusCode httpStatusCode;
    private final int code;
    private final String message;

}
