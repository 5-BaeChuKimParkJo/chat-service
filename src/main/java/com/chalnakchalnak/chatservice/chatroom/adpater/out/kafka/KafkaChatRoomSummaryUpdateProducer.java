package com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.ChatRoomSummaryUpdateEvent;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.PublishChatRoomSummaryUpdatePort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaChatRoomSummaryUpdateProducer implements PublishChatRoomSummaryUpdatePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "chat-service.chatroom-summary.update";

    @Override
    public void publishChatRoomSummaryUpdate(ChatRoomSummaryUpdateEvent chatRoomSummaryUpdateEvent) {
        try {
            final String payload = toJson(chatRoomSummaryUpdateEvent);

            kafkaTemplate.send(TOPIC, chatRoomSummaryUpdateEvent.getChatRoomUuid(), payload);

        } catch (Exception e) {
            log.error("Kafka 채팅방 요약 업데이트 이벤트 발행 실패", e);
            throw new BaseException(BaseResponseStatus.FAILED_PUBLISH_CHAT_ROOM_SUMMARY_UPDATE);
        }
    }

    private String toJson(ChatRoomSummaryUpdateEvent chatRoomSummaryUpdateEvent) {
        try {
            return objectMapper.writeValueAsString(chatRoomSummaryUpdateEvent);
        } catch (JsonProcessingException e) {
            log.error("채팅방 요약 업데이트 이벤트 직렬화 실패: {}", e.getMessage());
            throw new BaseException(BaseResponseStatus.FAILED_SERIALIZE_MESSAGE);
        }
    }
}
