package com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomSummaryUpdateEventType {
    MESSAGE_UPDATE("메시지 업데이트"),
    READ_UPDATE("읽음 업데이트"),
    ROOM_EXIT("채팅방 퇴장 업데이트");

    @JsonValue
    private final String label;
}
