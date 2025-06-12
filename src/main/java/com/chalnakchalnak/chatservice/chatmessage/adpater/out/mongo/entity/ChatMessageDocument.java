package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_message")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDocument {

    @Id
    private ObjectId id;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private LocalDateTime sentAt;

    @Builder
    public ChatMessageDocument(ObjectId id, String chatRoomUuid, String senderUuid, String message, LocalDateTime sentAt) {
        this.id = id;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.sentAt = sentAt;
    }
}
