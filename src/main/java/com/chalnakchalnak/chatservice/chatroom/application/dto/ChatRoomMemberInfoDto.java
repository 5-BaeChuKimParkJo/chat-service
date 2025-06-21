package com.chalnakchalnak.chatservice.chatroom.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomMemberInfoDto {

    private String memberUuid;
    private String role;

    @Builder
    public ChatRoomMemberInfoDto(String memberUuid, String role) {
        this.memberUuid = memberUuid;
        this.role = role;
    }
}
