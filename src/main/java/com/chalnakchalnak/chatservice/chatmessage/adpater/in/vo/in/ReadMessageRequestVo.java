package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadMessageRequestVo {

    @NotBlank(message = "chatRoomUuid 는 필수 값입니다.")
    private String chatRoomUuid;

    @NotBlank(message = "memberUuid 는 필수 값입니다.")
    private String memberUuid;

    private LocalDateTime lastReadMessageSentAt;

}
