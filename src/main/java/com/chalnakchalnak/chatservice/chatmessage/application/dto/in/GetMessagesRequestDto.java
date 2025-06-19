package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMessagesRequestDto {

    private String chatRoomUuid;
    private String memberUuid;
    private String lastMessageId;
    private Integer limit;

    @Builder
    public GetMessagesRequestDto(String chatRoomUuid, String memberUuid, String lastMessageId, Integer limit) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.lastMessageId = lastMessageId;
        this.limit = limit;
    }
}
