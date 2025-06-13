package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateReadCheckPointRequestDto {

    private String chatRoomUuid;
    private String memberUuid;
    private String lastReadMessageId;

    @Builder
    public UpdateReadCheckPointRequestDto(String chatRoomUuid, String memberUuid, String lastReadMessageId) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.lastReadMessageId = lastReadMessageId;
    }
}
