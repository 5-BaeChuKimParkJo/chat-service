package com.chalnakchalnak.chatservice.chatmessage.domain;

import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessage {

    private String roomUuid;
    private String senderUuid;
    private String message;
    private MessageType messageType;
    private LocalDateTime sentAt;

    @Builder
    public ChatMessage(String roomUuid, String senderUuid, String message, MessageType messageType, LocalDateTime sentAt) {
        this.roomUuid = roomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
    }
}
