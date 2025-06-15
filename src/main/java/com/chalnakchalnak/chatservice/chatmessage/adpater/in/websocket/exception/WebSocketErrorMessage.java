package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception;

import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
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
}
