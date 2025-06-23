package com.chalnakchalnak.chatservice.chatroom.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetChatRoomListByPostRequestDto {

    private String postUuid;

    @Builder
    public GetChatRoomListByPostRequestDto(String postUuid) {
        this.postUuid = postUuid;
    }
}
