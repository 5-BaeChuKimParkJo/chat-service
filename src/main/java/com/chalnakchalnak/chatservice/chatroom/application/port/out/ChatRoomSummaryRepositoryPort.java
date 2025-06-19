package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.ChatRoomSummaryUpdateEvent;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;

import java.util.List;

public interface ChatRoomSummaryRepositoryPort {

    List<GetChatRoomSummaryResponseDto> getMyChatRoomList(String memberUuid);
    void deleteChatRoomSummary(ExitChatRoomRequestDto exitChatRoomRequestDto);

}
