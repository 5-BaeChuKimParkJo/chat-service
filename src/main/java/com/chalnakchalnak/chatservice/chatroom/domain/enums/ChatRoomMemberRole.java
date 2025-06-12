package com.chalnakchalnak.chatservice.chatroom.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomMemberRole {

    SELLER("판매자"),
    BUYER("구매자");

    private final String label;
}
