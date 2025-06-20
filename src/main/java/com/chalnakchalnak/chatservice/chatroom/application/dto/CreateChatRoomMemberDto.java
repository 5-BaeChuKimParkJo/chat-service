package com.chalnakchalnak.chatservice.chatroom.application.dto;

import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomMemberRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateChatRoomMemberDto {

    private String chatRoomUuid;
    private String sellerUuid;
    private String buyerUuid;

    @Builder
    public CreateChatRoomMemberDto(String chatRoomUuid, String sellerUuid, String buyerUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.sellerUuid = sellerUuid;
        this.buyerUuid = buyerUuid;
    }
}
