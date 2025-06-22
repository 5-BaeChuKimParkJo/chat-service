package com.chalnakchalnak.chatservice.chatmessage.application.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMessagesResponseDto {

    private String messageUuid;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String messageType;
    private LocalDateTime sentAt;

    @Builder
    public GetMessagesResponseDto(String messageUuid, String chatRoomUuid, String senderUuid, String message, String messageType, LocalDateTime sentAt) {
        this.messageUuid = messageUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
    }
}
