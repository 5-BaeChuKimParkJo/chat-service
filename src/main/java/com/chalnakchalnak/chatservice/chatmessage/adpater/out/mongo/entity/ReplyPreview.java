package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity;

import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyPreview {

    private String senderUuid;
    private String message;
    private MessageType messageType;

    @Builder
    public ReplyPreview(String senderUuid, String message, MessageType messageType) {
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
    }
}
