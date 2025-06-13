package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMessagesRequestDto {

    private String chatRoomUuid;
    private String lastMessageId;
    private Integer limit;

    @Builder
    public GetMessagesRequestDto(String chatRoomUuid, String lastMessageId, Integer limit) {
        this.chatRoomUuid = chatRoomUuid;
        this.lastMessageId = lastMessageId;
        this.limit = limit;
    }
}
