package com.chalnakchalnak.chatservice.chatroom.application.service;

import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomSummaryUseCase;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomSummaryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomSummaryService implements ChatRoomSummaryUseCase {

    private final ChatRoomSummaryRepositoryPort chatRoomSummaryRepositoryPort;

    @Override
    public List<GetChatRoomSummaryResponseDto> getMyChatRoomList(String memberUuid){

        return chatRoomSummaryRepositoryPort.getMyChatRoomList(memberUuid);
    }

    @Override
    public int getMyUnreadCount(String memberUuid) {
        return chatRoomSummaryRepositoryPort.getMyUnreadCount(memberUuid)
                .stream()
                .mapToInt(GetChatRoomSummaryResponseDto::getUnreadCount)
                .sum();
    }
}
