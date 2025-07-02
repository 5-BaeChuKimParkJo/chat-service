package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis.pub.RedisMessagePublisher;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class WebSocketExceptionHandler {

    private final RedisMessagePublisher redisMessagePublisher;

    @MessageExceptionHandler(BaseException.class)
    public void handleWebSocketBaseException(BaseException ex) {
        log.warn("WebSocket 처리 중 에러 발생: {}", ex.getMessage());

        log.warn("WebSocket 처리 중 에러 발생: {}", ex.getMessage());

        String memberUuid = ex.getMemberUuid();
        if (memberUuid == null || memberUuid.isBlank()) {
            log.error("BaseException에 memberUuid가 없어 에러 전송 불가");
            return;
        }

        BaseResponseStatus status = ex.getStatus();
        WebSocketErrorMessage errorMessage = new WebSocketErrorMessage(status);

        redisMessagePublisher.publishError(memberUuid, errorMessage.toJson());
    }
}
