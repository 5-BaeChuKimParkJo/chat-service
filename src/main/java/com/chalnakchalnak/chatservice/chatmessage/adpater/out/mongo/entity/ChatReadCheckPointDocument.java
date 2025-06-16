package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_read_checkpoint")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatReadCheckPointDocument {
    @Id
    private String id;
    private String chatRoomUuid;
    private String memberUuid;
    private LocalDateTime lastReadMessageSentAt;
    private LocalDateTime updatedAt;

    @Builder
    public ChatReadCheckPointDocument(String id, String chatRoomUuid, String memberUuid, LocalDateTime lastReadMessageSentAt, LocalDateTime updatedAt) {
        this.id = id;
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.lastReadMessageSentAt = lastReadMessageSentAt;
        this.updatedAt = updatedAt;
    }

    public void updateCheckpoint(LocalDateTime lastReadMessageSentAt, LocalDateTime updatedAt) {
        this.lastReadMessageSentAt = lastReadMessageSentAt;
        this.updatedAt = updatedAt;
    }
}