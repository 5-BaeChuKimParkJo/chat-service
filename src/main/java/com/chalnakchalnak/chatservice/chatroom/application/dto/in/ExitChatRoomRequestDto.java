package com.chalnakchalnak.chatservice.chatroom.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExitChatRoomRequestDto {

    private String memberUuid;
    private String chatRoomUuid;

    @Builder
    public ExitChatRoomRequestDto(String memberUuid, String chatRoomUuid) {
        this.memberUuid = memberUuid;
        this.chatRoomUuid = chatRoomUuid;
    }
}
