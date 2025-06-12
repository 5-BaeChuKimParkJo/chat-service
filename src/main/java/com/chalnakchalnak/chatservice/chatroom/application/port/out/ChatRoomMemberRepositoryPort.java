package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;

import java.util.Optional;

public interface ChatRoomMemberRepositoryPort {

    void saveChatRoomMembers(CreateChatRoomMemberDto createChatRoomMemberDto);
    Optional<Long> findPrivateRoomId(String postUuid, String buyerUuid);
}
