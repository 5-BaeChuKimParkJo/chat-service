package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadMessageRequestDto {

    private String chatRoomUuid;
    private String memberUuid;
    private LocalDateTime lastReadMessageSentAt;

    @Builder
    public ReadMessageRequestDto(String chatRoomUuid, String memberUuid, LocalDateTime lastReadMessageSentAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.lastReadMessageSentAt = lastReadMessageSentAt;
    }
}
