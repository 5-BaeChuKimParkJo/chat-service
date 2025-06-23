package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChatRoomInfoRequestVo {

    @NotBlank(message = "chatRoomUuid는 필수 값 입니다.")
    private String chatRoomUuid;
}
