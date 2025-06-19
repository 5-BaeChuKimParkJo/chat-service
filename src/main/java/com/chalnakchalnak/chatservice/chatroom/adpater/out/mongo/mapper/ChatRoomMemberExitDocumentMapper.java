package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.document.ChatRoomMemberExitDocument;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberExitDto;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomMemberExitDocumentMapper {

    public ChatRoomMemberExitDto toChatRoomMemberExitDto(ChatRoomMemberExitDocument chatRoomMemberExitDocument) {
        return ChatRoomMemberExitDto.builder()
                .chatRoomUuid(chatRoomMemberExitDocument.getChatRoomUuid())
                .memberUuid(chatRoomMemberExitDocument.getMemberUuid())
                .exitedAt(chatRoomMemberExitDocument.getExitedAt())
                .build();
    }
}
