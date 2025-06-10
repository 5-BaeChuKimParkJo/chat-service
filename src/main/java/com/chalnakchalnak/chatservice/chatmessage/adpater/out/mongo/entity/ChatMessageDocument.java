package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_message")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDocument {

    @Id
    private String id;

    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private LocalDateTime sentAt;

    @Builder
    public ChatMessageDocument(String id, String chatRoomUuid, String senderUuid, String message, LocalDateTime sentAt) {
        this.id = id;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.sentAt = sentAt;
    }
}
