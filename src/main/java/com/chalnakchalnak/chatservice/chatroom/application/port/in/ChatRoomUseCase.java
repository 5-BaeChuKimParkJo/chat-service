package com.chalnakchalnak.chatservice.chatroom.application.port.in;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomInfoRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomListByPostRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomInfoResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomListByPostResponseDto;

import java.util.List;

public interface ChatRoomUseCase {

    String createPrivateChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto);
    void exitChatRoom(ExitChatRoomRequestDto exitChatRoomRequestDto);
    GetChatRoomInfoResponseDto getChatRoomInfo(GetChatRoomInfoRequestDto getChatRoomInfoRequestDto);
    List<GetChatRoomListByPostResponseDto> getChatRoomListByPost(GetChatRoomListByPostRequestDto getChatRoomListByPostRequestDto);
}
