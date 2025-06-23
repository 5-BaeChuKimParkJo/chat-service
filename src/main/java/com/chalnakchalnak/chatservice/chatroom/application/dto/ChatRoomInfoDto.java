package com.chalnakchalnak.chatservice.chatroom.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomInfoDto {

    private String chatRoomUuid;
    private String postUuid;
    private String chatRoomType;

    @Builder
    public ChatRoomInfoDto(String chatRoomUuid, String postUuid, String chatRoomType) {
        this.chatRoomUuid = chatRoomUuid;
        this.postUuid = postUuid;
        this.chatRoomType = chatRoomType;
    }
}
