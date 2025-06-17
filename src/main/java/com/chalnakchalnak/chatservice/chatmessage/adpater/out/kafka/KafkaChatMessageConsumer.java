package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatMessageConsumer {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepositoryPort chatMessageRepositoryPort;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "chat.private.room")
    public void consume(String payload, Acknowledgment ack) {
        try {
            ChatMessageDto message = objectMapper.readValue(payload, ChatMessageDto.class);

            log.info("🔍 Saving sentAt: {}", message.getSentAt());
            chatMessageRepositoryPort.processMessage(message);

            messagingTemplate.convertAndSend("/topic/chatroom/" + message.getChatRoomUuid(), message);

            ack.acknowledge();
            log.info("Kafka 메시지 처리 성공: {}", message.getChatRoomUuid());
        } catch (DuplicateKeyException e) {
            ack.acknowledge();
            log.warn("중복된 메시지 수신, 전송 생략: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Kafka 메시지 소비 실패, 재시도 수행", e);
        }
    }
}