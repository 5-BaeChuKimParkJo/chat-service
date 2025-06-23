package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChatRoomListByPostRequestVo {

    @NotBlank(message = "postUuid는 필수 값입니다.")
    private String postUuid;
}
