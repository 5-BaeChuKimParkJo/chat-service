package com.chalnakchalnak.chatservice.chatroom.domain.enums;

import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ChatRoomType {

    AUCTION_PRIVATE("경매 1대1 채팅방"),
    NORMAL_PRIVATE("일반 1대1 채팅방");

    private final String label;

    @JsonCreator
    public static ChatRoomType from(String value) {
        return Arrays.stream(ChatRoomType.values())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_CHAT_ROOM_TYPE));
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
