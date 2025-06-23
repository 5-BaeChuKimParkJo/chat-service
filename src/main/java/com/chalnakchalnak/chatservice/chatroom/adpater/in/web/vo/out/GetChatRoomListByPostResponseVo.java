package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetChatRoomListByPostResponseVo {

    private String chatRoomUuid;

    @Builder
    public GetChatRoomListByPostResponseVo(String chatRoomUuid) {
        this.chatRoomUuid = chatRoomUuid;
    }
}
