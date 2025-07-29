package com.chalnakchalnak.chatservice.chatmessage.adpater.out.websocket.message;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis.pub.RedisMessagePublisher;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.SendMessageToClientPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendMessageToClient implements SendMessageToClientPort {

    private final RedisMessagePublisher redisMessagePublisher;
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(ReadMessageRequestDto readMessageRequestDto, String opponentUuid) {
        try {
            String message = objectMapper.writeValueAsString(readMessageRequestDto);
            redisMessagePublisher.publishRead(opponentUuid, message);
        } catch (Exception e) {
            log.error("클라이언트 메시지 수신 실패: {}", e.getMessage());
        }
    }
}
