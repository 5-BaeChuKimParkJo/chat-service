package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_room_summary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatRoomSummaryDocument {

    @Id
    private String id;

    private String chatRoomUuid;
    private String memberUuid;
    private String opponentUuid;
    private String lastMessage;
    private LocalDateTime lastMessageSentAt;
    private String messageType;
    private int unreadCount;
    private LocalDateTime updatedAt;

    @Builder
    public ChatRoomSummaryDocument(
            String chatRoomUuid,
            String memberUuid,
            String opponentUuid,
            String lastMessage,
            LocalDateTime lastMessageSentAt,
            String messageType,
            int unreadCount,
            LocalDateTime updatedAt
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.opponentUuid = opponentUuid;
        this.lastMessage = lastMessage;
        this.lastMessageSentAt = lastMessageSentAt;
        this.messageType = messageType;
        this.unreadCount = unreadCount;
        this.updatedAt = updatedAt;
    }
}
