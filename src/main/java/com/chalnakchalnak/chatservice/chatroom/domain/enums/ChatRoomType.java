package com.chalnakchalnak.chatservice.chatroom.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomType {

    AUCTION_GROUP("경매 단체 채팅방"),
    AUCTION_PRIVATE("경매 1대1 채팅방"),
    NORMAL_PRIVATE("일반 1대1 채팅방");

    private final String label;
}
