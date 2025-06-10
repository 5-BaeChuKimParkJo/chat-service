package com.chalnakchalnak.chatservice.chatmessage.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessage {

    private String roomUuid;
    private String senderUuid;
    private String message;
    private LocalDateTime sentAt;

    @Builder
    public ChatMessage(String roomUuid, String senderUuid, String message, LocalDateTime sentAt) {
        this.roomUuid = roomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.sentAt = sentAt;
    }
}
