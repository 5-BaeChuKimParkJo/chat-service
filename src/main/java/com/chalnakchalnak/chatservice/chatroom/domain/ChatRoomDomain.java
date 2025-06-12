package com.chalnakchalnak.chatservice.chatroom.domain;

import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomDomain {

    private String postUuid;
    private String chatRoomUuid;
    private ChatRoomType chatRoomType;

    @Builder
    public ChatRoomDomain(String postUuid, String chatRoomUuid, ChatRoomType chatRoomType) {
        this.postUuid = postUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.chatRoomType = chatRoomType;
    }
}
