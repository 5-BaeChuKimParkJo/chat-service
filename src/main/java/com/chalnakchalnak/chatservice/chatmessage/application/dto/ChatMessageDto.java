package com.chalnakchalnak.chatservice.chatmessage.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime sentAt;

    @Builder
    public ChatMessageDto(String chatRoomUuid, String senderUuid, String nickname, String message, LocalDateTime sentAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.nickname = nickname;
        this.message = message;
        this.sentAt = sentAt;
    }
}
