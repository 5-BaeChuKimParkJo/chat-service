package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberExitDto;

import java.util.Optional;

public interface ChatRoomMemberExitRepositoryPort {

    Optional<ChatRoomMemberExitDto> findByChatRoomUuidAndMemberUuid(String chatRoomUuid, String memberUuid);
}
