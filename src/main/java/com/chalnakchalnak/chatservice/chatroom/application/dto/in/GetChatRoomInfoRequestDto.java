package com.chalnakchalnak.chatservice.chatroom.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetChatRoomInfoRequestDto {

    private String chatRoomUuid;

    @Builder
    public GetChatRoomInfoRequestDto(String chatRoomUuid) {
        this.chatRoomUuid = chatRoomUuid;
    }
}
