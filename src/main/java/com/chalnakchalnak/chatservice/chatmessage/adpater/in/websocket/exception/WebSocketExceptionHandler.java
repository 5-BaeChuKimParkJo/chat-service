package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception;

import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class WebSocketExceptionHandler {

    @MessageExceptionHandler(BaseException.class)
    @SendToUser("/queue/errors")
    public WebSocketErrorMessage handleWebSocketBaseException(BaseException ex) {
        log.warn("WebSocket 처리 중 에러 발생: {}", ex.getMessage());

        BaseResponseStatus status = ex.getStatus();
        return new WebSocketErrorMessage(status);
    }
}
