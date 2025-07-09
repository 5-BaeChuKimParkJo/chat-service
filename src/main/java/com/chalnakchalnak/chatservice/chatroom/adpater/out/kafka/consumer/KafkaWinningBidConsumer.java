package com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.consumer;


import com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis.pub.RedisMessagePublisher;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaWinningBidConsumer {

    private final ChatRoomUseCase chatRoomUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "auction-service.winning-bid")
    public void consume(String payload, Acknowledgment ack) {

        try {
            CreateChatRoomRequestDto chatRoomDto = objectMapper.readValue(payload, CreateChatRoomRequestDto.class);

            try {
                chatRoomUseCase.createPrivateChatRoom(chatRoomDto);
            } catch (DuplicateKeyException e) {
                log.warn("중복 채팅방, 생성 생략: {}", e.getMessage());
                ack.acknowledge();
            } catch (BaseException e) {
                log.error("채팅방 생성 실패 - 치명적인 도메인 에러: {}", e.getMessage());
            } catch (Exception e) {
                log.error("채팅방 생성 중 예외 발생", e);
            }

            ack.acknowledge();
        } catch (Exception e) {
            log.error("Kafka 메시지 처리 실패, 재시도 수행", e);
        }
    }
}
