package com.chalnakchalnak.chatservice.chatroom.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomMemberExitDto {

    private String chatRoomUuid;
    private String memberUuid;
    private LocalDateTime exitedAt;

    @Builder
    public ChatRoomMemberExitDto(String chatRoomUuid, String memberUuid, LocalDateTime exitedAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.exitedAt = exitedAt;
    }
}
