package com.chalnakchalnak.chatservice.chatmessage.application.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetReadCheckPointResponseDto {

    private String lastReadMessageSentAt;

    @Builder
    public GetReadCheckPointResponseDto(String lastReadMessageSentAt) {
        this.lastReadMessageSentAt = lastReadMessageSentAt;
    }
}
