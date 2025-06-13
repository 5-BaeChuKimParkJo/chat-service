package com.chalnakchalnak.chatservice.chatroom.application.dto.in;

import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateChatRoomRequestDto {

    private String postUuid;
    private String sellerUuid;
    private String buyerUuid;
    private ChatRoomType chatRoomType;

    @Builder
    public CreateChatRoomRequestDto(String postUuid, String sellerUuid, String buyerUuid, ChatRoomType chatRoomType) {
        this.postUuid = postUuid;
        this.sellerUuid = sellerUuid;
        this.buyerUuid = buyerUuid;
        this.chatRoomType = chatRoomType;
    }
}
