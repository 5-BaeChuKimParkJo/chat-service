package com.chalnakchalnak.chatservice.chatroom.application.port.in;

import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;

import java.util.List;

public interface ChatRoomSummaryUseCase {

    List<GetChatRoomSummaryResponseDto> getMyChatRoomList(String memberUuid);
}
