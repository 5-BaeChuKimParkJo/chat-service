package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import com.chalnakchalnak.chatservice.chatmessage.domain.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMessageRequestVo {

    @NotBlank(message = "ChatRoomUuid는 필수 입력값입니다.")
    private String chatRoomUuid;

    @NotBlank(message = "SenderUuid는 필수 입력값입니다.")
    private String senderUuid;

    @NotBlank(message = "Message는 필수 입력값입니다.")
    @Size(max = 5000, message = "메시지는 최대 5000자까지 입력 가능합니다.")
    private String message;

    @NotNull(message = "MessageType은 필수 입력값입니다.")
    private MessageType messageType;
}
