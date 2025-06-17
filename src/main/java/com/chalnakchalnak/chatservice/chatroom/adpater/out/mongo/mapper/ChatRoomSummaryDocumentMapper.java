package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.entity.ChatRoomSummaryDocument;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomSummaryDocumentMapper {

    public GetChatRoomSummaryResponseDto toGetChatRoomSummaryResponseDto(ChatRoomSummaryDocument chatRoomSummaryDocument) {
        return GetChatRoomSummaryResponseDto.builder()
                .chatRoomUuid(chatRoomSummaryDocument.getChatRoomUuid())
                .opponentUuid(chatRoomSummaryDocument.getOpponentUuid())
                .lastMessage(chatRoomSummaryDocument.getLastMessage())
                .lastMessageSentAt(chatRoomSummaryDocument.getLastMessageSentAt())
                .unreadCount(chatRoomSummaryDocument.getUnreadCount())
                .build();
    }
}
