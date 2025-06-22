package com.chalnakchalnak.chatservice.chatroom.application.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetChatRoomListByPostResponseDto {

    private String chatRoomUuid;

    @Builder
    public GetChatRoomListByPostResponseDto(String chatRoomUuid) {
        this.chatRoomUuid = chatRoomUuid;
    }
}
