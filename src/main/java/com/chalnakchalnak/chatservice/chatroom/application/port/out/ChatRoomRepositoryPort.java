package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomInfoRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomListByPostRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomListByPostResponseDto;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepositoryPort {

    String createChatRoom(CreateChatRoomDto createChatRoomDto);
    Optional<String> findRoomUuidById(Long roomId);
    Optional<ChatRoomInfoDto> getChatRoomInfo(GetChatRoomInfoRequestDto getChatRoomInfoRequestDto);
    List<GetChatRoomListByPostResponseDto> getChatRoomListByPost(GetChatRoomListByPostRequestDto getChatRoomListByPostRequestDto);
}
