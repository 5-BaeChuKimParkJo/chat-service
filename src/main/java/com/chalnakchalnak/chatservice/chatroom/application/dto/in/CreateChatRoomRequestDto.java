package com.chalnakchalnak.chatservice.chatroom.application.dto.in;

import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateChatRoomRequestDto {

    private String postUuid;
    private String sellerUuid;
    private String buyerUuid;
    private ChatRoomType chatRoomType;

}
