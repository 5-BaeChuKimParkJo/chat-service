package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception.WebSocketErrorMessage;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka.mapper.KafkaEventDtoMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.GenerateUuidPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaChatMessageProducer implements PublishChatMessagePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;
    private final KafkaEventDtoMapper kafkaEventDtoMapper;
    private final GenerateUuidPort generateUuidPort;

    private final String TOPIC_NAME = "chat.private.room";

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private int TIMEOUT_MILLISECONDS;

    @Override
    public void publishChatMessage(SendMessageRequestDto sendMessageRequestDto) {
        final ChatMessageDto chatMessageDto = kafkaEventDtoMapper.toChatMessageDto(
                sendMessageRequestDto, generateUuidPort.generateUuid()
        );

        final String payload = toJson(chatMessageDto);

        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC_NAME, chatMessageDto.getChatRoomUuid(), payload);

        future.orTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Kafka 전송 실패", ex);
                        sendErrorToUser(chatMessageDto.getSenderUuid());
                    } else {
                        log.info("Kafka 전송 성공");
                    }
                });

    }

    private String toJson(ChatMessageDto chatMessageDto) {
        try {
            return objectMapper.writeValueAsString(chatMessageDto);
        } catch (JsonProcessingException e) {
            log.error("Kafka 메시지 직렬화 실패: {}", chatMessageDto, e);
            throw new BaseException(BaseResponseStatus.FAILED_SERIALIZE_MESSAGE);
        }
    }

    private void sendErrorToUser(String memberUuid) {
        messagingTemplate.convertAndSendToUser(
                memberUuid,
                "/queue/errors",
                new WebSocketErrorMessage(BaseResponseStatus.FAILED_PUBLISH_MESSAGE)
        );
    }
}