package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;

import java.util.List;

public interface ChatRoomSummaryRepositoryPort {

    List<GetChatRoomSummaryResponseDto> getMyChatRoomList(String memberUuid);
}
