package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.mapper;

import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.out.GetChatRoomSummaryResponseVo;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomSummaryVoMapper {

    public GetChatRoomSummaryResponseVo toGetChatRoomSummaryResponseVo(GetChatRoomSummaryResponseDto chatRoomSummaryResponseDto) {
        return GetChatRoomSummaryResponseVo.builder()
                .chatRoomUuid(chatRoomSummaryResponseDto.getChatRoomUuid())
                .opponentUuid(chatRoomSummaryResponseDto.getOpponentUuid())
                .lastMessage(chatRoomSummaryResponseDto.getLastMessage())
                .messageType(chatRoomSummaryResponseDto.getMessageType())
                .lastMessageSentAt(chatRoomSummaryResponseDto.getLastMessageSentAt())
                .unreadCount(chatRoomSummaryResponseDto.getUnreadCount())
                .build();
    }
}
