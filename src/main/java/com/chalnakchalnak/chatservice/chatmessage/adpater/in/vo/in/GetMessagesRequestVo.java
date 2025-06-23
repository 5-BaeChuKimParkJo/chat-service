package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMessagesRequestVo {

    @NotBlank(message = "채팅방 UUID는 필수입니다.")
    private String chatRoomUuid;

    private String lastMessageId;

    private Integer limit;

}
