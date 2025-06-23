package com.chalnakchalnak.chatservice.chatmessage.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyPreviewDto {

    private String senderUuid;
    private String message;
    private String messageType;

    @Builder
    public ReplyPreviewDto(String senderUuid, String message, String messageType) {
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
    }
}
