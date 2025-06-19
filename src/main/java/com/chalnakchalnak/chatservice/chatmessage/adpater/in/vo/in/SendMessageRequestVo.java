package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import com.chalnakchalnak.chatservice.chatmessage.domain.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "MessageType은 필수 입력값입니다.")
    private MessageType messageType;
}
