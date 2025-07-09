package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_message")
@CompoundIndexes({
        @CompoundIndex(name = "idx_message_sentAt", def = "{'messageUuid': 1, 'sentAt': -1}")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDocument {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String messageUuid;

    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String messageType;
    private LocalDateTime sentAt;
    private String replyToMessageUuid;
    private ReplyPreview replyPreview;

    @Builder
    public ChatMessageDocument(ObjectId id, String messageUuid, String chatRoomUuid,
                               String senderUuid, String message, String messageType,
                               LocalDateTime sentAt, String replyToMessageUuid, ReplyPreview replyPreview
    ) {
        this.id = id;
        this.messageUuid = messageUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
        this.replyToMessageUuid = replyToMessageUuid;
        this.replyPreview = replyPreview;
    }
}
