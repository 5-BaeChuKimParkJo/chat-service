package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka.consumer;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis.pub.RedisMessagePublisher;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatMessageConsumer {

    private final RedisMessagePublisher redisMessagePublisher;
    private final ChatMessageRepositoryPort chatMessageRepositoryPort;
    private final ObjectMapper objectMapper;

//    @Transactional
    @KafkaListener(topics = "chat.private.room")
    public void consume(List<String> payloads, Acknowledgment ack) {

        try {
            List<ChatMessageDto> messageList = new ArrayList<>();
            for (String payload : payloads) {
                ChatMessageDto message = objectMapper.readValue(payload, ChatMessageDto.class);
                messageList.add(message);
            }

            chatMessageRepositoryPort.bulkSaveMessages(messageList);
            chatMessageRepositoryPort.bulkUpsertSummary(messageList);

            for (ChatMessageDto message : messageList) {
                CompletableFuture.runAsync(() -> {
                    try {
                        redisMessagePublisher.publish(message.getChatRoomUuid(), objectMapper.writeValueAsString(message));
                    } catch (Exception e) {
                        log.error("Redis 메시지 발행 실패", e.getMessage());
                        throw new BaseException(BaseResponseStatus.REDIS_PUBLISH_ERROR);
                    }
                });
            }

            ack.acknowledge();
        } catch (DuplicateKeyException e) {
            ack.acknowledge();
            log.warn("중복된 메시지 수신, 전송 생략: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Kafka 메시지 소비 실패, 재시도 수행", e);
        }
    }
}