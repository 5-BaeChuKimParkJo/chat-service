package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
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
    private String replyToMessageUuid;

    @Builder
    public SendMessageRequestDto(String chatRoomUuid, String senderUuid, String message,
                                 MessageType messageType, LocalDateTime sentAt, String replyToMessageUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
        this.replyToMessageUuid = replyToMessageUuid;
    }
}
