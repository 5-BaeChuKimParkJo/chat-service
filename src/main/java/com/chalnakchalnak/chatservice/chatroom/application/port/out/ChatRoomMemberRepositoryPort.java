package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomInfoRequestDto;

import java.util.List;
import java.util.Optional;

public interface ChatRoomMemberRepositoryPort {

    void saveChatRoomMembers(CreateChatRoomMemberDto createChatRoomMemberDto);
    Optional<Long> findPrivateChatRoomUuid(String postUuid, String buyerUuid);
    String findOpponentUuid(String chatRoomUuid, String myMemberUuid);
    Optional<List<ChatRoomMemberInfoDto>> getChatRoomMembers(GetChatRoomInfoRequestDto getChatRoomInfoRequestDto);
}
