package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka.mapper;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventDtoMapper {

    public ChatMessageDto toChatMessageDto(SendMessageRequestDto sendMessageRequestDto, String uuid) {
        return ChatMessageDto.builder()
                .messageUuid(uuid)
                .chatRoomUuid(sendMessageRequestDto.getChatRoomUuid())
                .senderUuid(sendMessageRequestDto.getSenderUuid())
                .message(sendMessageRequestDto.getMessage())
                .messageType(sendMessageRequestDto.getMessageType().toString())
                .sentAt(sendMessageRequestDto.getSentAt())
                .build();
    }
}
