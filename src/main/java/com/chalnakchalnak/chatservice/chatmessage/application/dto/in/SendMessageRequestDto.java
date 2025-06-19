package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import com.chalnakchalnak.chatservice.chatmessage.domain.MessageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMessageRequestDto {

    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private MessageType messageType;
    private LocalDateTime sentAt;

    @Builder
    public SendMessageRequestDto(String chatRoomUuid, String senderUuid, String message, MessageType messageType, LocalDateTime sentAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
    }
}
