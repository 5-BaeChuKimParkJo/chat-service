package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatMessageDocumentMapper {

    public ChatMessageDocument toChatMessageDocument(SendMessageRequestDto sendMessageRequestDto) {
        return ChatMessageDocument.builder()
                .chatRoomUuid(sendMessageRequestDto.getChatRoomUuid())
                .senderUuid(sendMessageRequestDto.getSenderUuid())
                .nickname(sendMessageRequestDto.getNickname())
                .message(sendMessageRequestDto.getMessage())
                .sentAt(sendMessageRequestDto.getSentAt())
                .build();
    }

    public GetMessagesResponseDto toGetMessagesResponseDto(ChatMessageDocument chatMessageDocument) {
        return GetMessagesResponseDto.builder()
                .messageId(chatMessageDocument.getId().toHexString())
                .chatRoomUuid(chatMessageDocument.getChatRoomUuid())
                .senderUuid(chatMessageDocument.getSenderUuid())
                .nickname(chatMessageDocument.getNickname())
                .message(chatMessageDocument.getMessage())
                .sentAt(chatMessageDocument.getSentAt())
                .build();
    }
}
