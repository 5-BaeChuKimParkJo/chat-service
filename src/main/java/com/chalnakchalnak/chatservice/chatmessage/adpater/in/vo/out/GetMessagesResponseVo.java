package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMessagesResponseVo {

    private String messageId;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String sentAt;

    @Builder
    public GetMessagesResponseVo(String messageId, String chatRoomUuid, String senderUuid, String message, String sentAt) {
        this.messageId = messageId;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.sentAt = sentAt;
    }
}
