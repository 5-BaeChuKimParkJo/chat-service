package com.chalnakchalnak.chatservice.chatroom.application.dto.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetChatRoomInfoResponseDto {

    private String chatRoomUuid;
    private String postUuid;
    private String chatRoomType;
    private List<ChatRoomMemberInfoDto> members;

    @Builder
    public GetChatRoomInfoResponseDto(String chatRoomUuid, String postUuid, String chatRoomType, List<ChatRoomMemberInfoDto> members) {
        this.chatRoomUuid = chatRoomUuid;
        this.postUuid = postUuid;
        this.chatRoomType = chatRoomType;
        this.members = members;
    }

}
