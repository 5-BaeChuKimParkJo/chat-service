package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import com.chalnakchalnak.chatservice.chatmessage.domain.MessageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMessagesResponseVo {

    private String messageUuid;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String messageType;
    private String sentAt;

    @Builder
    public GetMessagesResponseVo(String messageUuid, String chatRoomUuid, String senderUuid, String message, String messageType, String sentAt) {
        this.messageUuid = messageUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
    }
}
