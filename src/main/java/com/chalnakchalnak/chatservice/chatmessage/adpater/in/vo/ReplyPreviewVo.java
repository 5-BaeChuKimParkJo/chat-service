package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyPreviewVo {
    private String senderUuid;
    private String message;
    private String messageType;

    @Builder
    public ReplyPreviewVo(String senderUuid, String message, String messageType) {
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
    }
}
