package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetChatRoomSummaryResponseVo {

    private String chatRoomUuid;
    private String opponentUuid;
    private String lastMessage;
    private String messageType;
    private LocalDateTime lastMessageSentAt;
    private int unreadCount;

    @Builder
    public GetChatRoomSummaryResponseVo(
            String chatRoomUuid,
            String opponentUuid,
            String lastMessage,
            String messageType,
            LocalDateTime lastMessageSentAt,
            int unreadCount
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.opponentUuid = opponentUuid;
        this.lastMessage = lastMessage;
        this.messageType = messageType;
        this.lastMessageSentAt = lastMessageSentAt;
        this.unreadCount = unreadCount;
    }
}
