package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception;

import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebSocketErrorMessage {
    private final int code;
    private final String message;

    public WebSocketErrorMessage(BaseResponseStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{\"code\":500,\"message\":\"에러 직렬화 실패\"}";
        }
    }
}
