package com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoomSummaryUpdateEvent {

    private String chatRoomUuid;
    private List<String> targetMemberUuids;
    private String eventType;

    @Builder
    public ChatRoomSummaryUpdateEvent(
            String chatRoomUuid,
            List<String> targetMemberUuids,
            String eventType
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.targetMemberUuids = targetMemberUuids;
        this.eventType = eventType;
    }
}
