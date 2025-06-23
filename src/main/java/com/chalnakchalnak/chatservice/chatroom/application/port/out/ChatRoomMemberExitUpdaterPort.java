package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;

public interface ChatRoomMemberExitUpdaterPort {

    void updateExitedAt(ExitChatRoomRequestDto exitChatRoomRequestDto);
}
