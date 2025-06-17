package com.chalnakchalnak.chatservice.chatmessage.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private String messageUuid;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime sentAt;

    @Builder
    public ChatMessageDto(String messageUuid, String chatRoomUuid, String senderUuid, String message, LocalDateTime sentAt) {
        this.messageUuid = messageUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.sentAt = sentAt;
    }


}
