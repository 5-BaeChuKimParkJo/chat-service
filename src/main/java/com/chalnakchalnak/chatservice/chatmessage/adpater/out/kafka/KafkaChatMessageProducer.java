package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka;

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
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaChatMessageProducer implements PublishChatMessagePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final KafkaEventDtoMapper kafkaEventDtoMapper;
    private final GenerateUuidPort generateUuidPort;

    private final String TOPIC_NAME = "chat.private.room";

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private int TIMEOUT_MILLISECONDS;

    @Override
    public Boolean publishChatMessage(SendMessageRequestDto sendMessageRequestDto) {
        final ChatMessageDto chatMessageDto = kafkaEventDtoMapper.toChatMessageDto(
                sendMessageRequestDto, generateUuidPort.generateUuid()
        );

        final String payload = toJson(chatMessageDto);

        try {
            CompletableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send("chat.private.room", chatMessageDto.getChatRoomUuid(), payload);

            SendResult<String, String> result = future.get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

            RecordMetadata meta = result.getRecordMetadata();
            log.info("Kafka 전송 성공: topic={}, partition={}, offset={}",
                    meta.topic(), meta.partition(), meta.offset());

            return true;

        } catch (TimeoutException e) {
            log.warn("Kafka 전송 타임아웃 (5초)", e);
            return false;
        } catch (Exception e) {
            log.error("Kafka 전송 실패", e);
            return false;
        }
    }

    private String toJson(ChatMessageDto chatMessageDto) {
        try {
            return objectMapper.writeValueAsString(chatMessageDto);
        } catch (JsonProcessingException e) {
            throw new BaseException(BaseResponseStatus.FAILED_SERIALIZE_MESSAGE);
        }
    }
}
