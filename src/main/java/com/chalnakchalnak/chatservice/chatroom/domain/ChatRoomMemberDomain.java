package com.chalnakchalnak.chatservice.chatroom.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomMemberDomain {

    private String chatRoomUuid;
    private String memberUuid;
    private LocalDateTime joinedAt;

    @Builder
    public ChatRoomMemberDomain(String chatRoomUuid, String memberUuid, LocalDateTime joinedAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.joinedAt = joinedAt;
    }
}
