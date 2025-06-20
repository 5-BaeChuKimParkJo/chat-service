package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in;

import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateChatRoomRequestVo {

    @NotBlank(message = "Post UUID는 필수 값입니다")
    private String postUuid;

    @NotBlank(message = "Seller UUID는 필수 값입니다")
    private String sellerUuid;

    @NotBlank(message = "Buyer UUID는 필수 값입니다")
    private String buyerUuid;

    @NotNull(message = "ChatRoomType은 필수 값입니다")
    private ChatRoomType chatRoomType;
}
