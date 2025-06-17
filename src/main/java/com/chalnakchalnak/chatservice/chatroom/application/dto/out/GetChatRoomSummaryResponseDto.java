package com.chalnakchalnak.chatservice.chatroom.application.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetChatRoomSummaryResponseDto {

    private String chatRoomUuid;
    private String opponentUuid;
    private String lastMessage;
    private LocalDateTime lastMessageSentAt;
    private int unreadCount;

    @Builder
    public GetChatRoomSummaryResponseDto(
            String chatRoomUuid,
            String opponentUuid,
            String lastMessage,
            LocalDateTime lastMessageSentAt,
            int unreadCount
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.opponentUuid = opponentUuid;
        this.lastMessage = lastMessage;
        this.lastMessageSentAt = lastMessageSentAt;
        this.unreadCount = unreadCount;
    }
}
