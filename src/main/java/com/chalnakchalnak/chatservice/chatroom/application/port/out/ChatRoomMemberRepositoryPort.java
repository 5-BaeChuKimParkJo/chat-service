package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;

import java.util.Optional;

public interface ChatRoomMemberRepositoryPort {

    void saveChatRoomMembers(CreateChatRoomMemberDto createChatRoomMemberDto);
    Optional<Long> findPrivateChatRoomUuid(String postUuid, String buyerUuid);
    String findOpponentUuid(String chatRoomUuid, String myMemberUuid);
}
