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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatMessageConsumer {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepositoryPort chatMessageRepositoryPort;
    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "chat.private.room")
    public void consume(List<String> payloads, Acknowledgment ack) {

        try {
            List<ChatMessageDto> messageList = new ArrayList<>();
            for (String payload : payloads) {
                ChatMessageDto message = objectMapper.readValue(payload, ChatMessageDto.class);
                messageList.add(message);
            }

            chatMessageRepositoryPort.bulkSaveMessages(messageList);
            chatMessageRepositoryPort.bulkUpsertMessages(messageList);

            for (ChatMessageDto message : messageList) {
                messagingTemplate.convertAndSend(
                        "/topic/chat/" + message.getChatRoomUuid(),
                        message
                );
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