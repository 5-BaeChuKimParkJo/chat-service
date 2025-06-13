package com.chalnakchalnak.chatservice.chatroom.application.port.in;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.CreateChatRoomResponseDto;

public interface ChatRoomUseCase {

    String createPrivateChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto);
}
