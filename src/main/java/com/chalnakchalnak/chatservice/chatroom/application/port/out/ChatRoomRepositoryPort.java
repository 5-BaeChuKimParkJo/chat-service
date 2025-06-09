package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;

import java.util.Optional;

public interface ChatRoomRepositoryPort {

    String createChatRoom(CreateChatRoomDto createChatRoomDto);
    Optional<String> findRoomUuidById(Long roomId);
}
