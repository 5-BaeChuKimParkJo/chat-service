package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMessageRequestDto {

    private String chatRoomUuid;
    private String senderUuid;
    private String nickname;
    private String message;
    private LocalDateTime sentAt;

    @Builder
    public SendMessageRequestDto(String chatRoomUuid, String senderUuid, String nickname, String message, LocalDateTime sentAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.nickname = nickname;
        this.message = message;
        this.sentAt = sentAt;
    }
}
