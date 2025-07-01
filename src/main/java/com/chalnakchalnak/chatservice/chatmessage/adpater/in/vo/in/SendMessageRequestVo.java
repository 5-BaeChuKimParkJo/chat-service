package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

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

    private String replyToMessageUuid;

    @AssertTrue(message = "REPLY 타입일 경우 replyToMessageId는 필수입니다.")
    private boolean isValidReplyCondition() {
        if (messageType == null) return true;
        if (messageType == MessageType.REPLY) {
            return replyToMessageUuid != null && !replyToMessageUuid.isBlank();
        } else {
            return replyToMessageUuid == null || replyToMessageUuid.isBlank();
        }
    }
}
