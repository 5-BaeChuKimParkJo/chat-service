package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.domain.MessageType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatMessageDocumentMapper {

    public ChatMessageDocument toChatMessageDocument(ChatMessageDto chatMessageDto) {
        return ChatMessageDocument.builder()
                .messageUuid(chatMessageDto.getMessageUuid())
                .chatRoomUuid(chatMessageDto.getChatRoomUuid())
                .senderUuid(chatMessageDto.getSenderUuid())
                .message(chatMessageDto.getMessage())
                .messageType(chatMessageDto.getMessageType())
                .sentAt(chatMessageDto.getSentAt())
                .build();
    }

    public GetMessagesResponseDto toGetMessagesResponseDto(ChatMessageDocument chatMessageDocument) {
        return GetMessagesResponseDto.builder()
                .messageId(chatMessageDocument.getId().toHexString())
                .chatRoomUuid(chatMessageDocument.getChatRoomUuid())
                .senderUuid(chatMessageDocument.getSenderUuid())
                .message(chatMessageDocument.getMessage())
                .messageType(chatMessageDocument.getMessageType())
                .sentAt(chatMessageDocument.getSentAt())
                .build();
    }
}
