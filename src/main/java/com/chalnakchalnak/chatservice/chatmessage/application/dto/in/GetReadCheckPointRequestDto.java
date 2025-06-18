package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetReadCheckPointRequestDto {

    private String chatRoomUuid;
    private String memberUuid;

    @Builder
    public GetReadCheckPointRequestDto(String chatRoomUuid, String memberUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
    }
}
