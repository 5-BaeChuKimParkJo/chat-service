package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class GetMessagesRequestDto {

    private String chatRoomUuid;
    private String memberUuid;
    private String lastMessageUuid;
    private LocalDateTime lastMessageSentAt;
    private Integer limit;

    @Builder
    public GetMessagesRequestDto(String chatRoomUuid, String memberUuid, String lastMessageUuid,
                                 LocalDateTime lastMessageSentAt, Integer limit) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.lastMessageUuid = lastMessageUuid;
        this.lastMessageSentAt = lastMessageSentAt;
        this.limit = limit;
    }
}
