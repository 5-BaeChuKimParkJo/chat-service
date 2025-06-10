package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageDocumentMapper {

    public ChatMessageDocument toChatMessageDocument(SendMessageRequestDto sendMessageRequestDto) {
        return ChatMessageDocument.builder()
                .chatRoomUuid(sendMessageRequestDto.getChatRoomUuid())
                .senderUuid(sendMessageRequestDto.getSenderUuid())
                .message(sendMessageRequestDto.getMessage())
                .sentAt(sendMessageRequestDto.getSentAt())
                .build();
    }
}
