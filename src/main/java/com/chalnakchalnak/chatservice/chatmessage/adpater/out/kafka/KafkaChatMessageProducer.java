package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaChatMessageProducer implements PublishChatMessagePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String chatMessageTopic = "chat.private.room";

    @Override
    public void publishChatMessage(SendMessageRequestDto sendMessageRequestDto) {
        System.out.println("KafkaChatMessageProducer.publishChatMessage called with: " + sendMessageRequestDto.getChatRoomUuid());
        kafkaTemplate.send(
                chatMessageTopic, sendMessageRequestDto.getChatRoomUuid(), toJson(sendMessageRequestDto)
        );
    }

    private String toJson(SendMessageRequestDto sendMessageRequestDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(sendMessageRequestDto);
        } catch (JsonProcessingException e) {
            throw new BaseException(BaseResponseStatus.FAILED_SERIALIZE_MESSAGE);
        }
    }
}
