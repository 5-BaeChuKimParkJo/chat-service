package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NextCursorVo {

    private String lastMessageUuid;
    private String lastMessageSentAt;

    @Builder
    public NextCursorVo(String lastMessageUuid, String lastMessageSentAt) {
        this.lastMessageUuid = lastMessageUuid;
        this.lastMessageSentAt = lastMessageSentAt;
    }
}
