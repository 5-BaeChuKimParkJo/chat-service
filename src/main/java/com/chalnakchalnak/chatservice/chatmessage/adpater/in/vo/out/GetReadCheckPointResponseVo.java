package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetReadCheckPointResponseVo {

    private String lastReadMessageSentAt;

    @Builder
    public GetReadCheckPointResponseVo(String lastReadMessageSentAt) {
        this.lastReadMessageSentAt = lastReadMessageSentAt;
    }
}
