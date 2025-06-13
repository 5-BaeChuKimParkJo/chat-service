package com.chalnakchalnak.chatservice.chatroom.application.dto;

import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateChatRoomDto {

    private String chatRoomUuid;
    private String postUuid;
    private ChatRoomType chatRoomType;

    @Builder
    public CreateChatRoomDto(String chatRoomUuid, String postUuid, ChatRoomType chatRoomType) {
        this.chatRoomUuid = chatRoomUuid;
        this.postUuid = postUuid;
        this.chatRoomType = chatRoomType;
    }
}
