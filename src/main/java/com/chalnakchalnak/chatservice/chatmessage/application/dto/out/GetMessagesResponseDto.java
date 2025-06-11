package com.chalnakchalnak.chatservice.chatmessage.application.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMessagesResponseDto {

    private String messageId;
    private String chatRoomUuid;
    private String senderUuid;
    private String nickname;
    private String message;
    private LocalDateTime sentAt;

    @Builder
    public GetMessagesResponseDto(String messageId, String chatRoomUuid, String senderUuid, String nickname, String message, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.nickname = nickname;
        this.message = message;
        this.sentAt = sentAt;
    }
}
