package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMessageRequestVo {

    @NotBlank(message = "ChatRoomUuid는 필수 입력값입니다.")
    private String chatRoomUuid;

    @NotBlank(message = "SenderUuid는 필수 입력값입니다.")
    private String senderUuid;

    @NotBlank(message = "Message는 필수 입력값입니다.")
    private String message;

}
