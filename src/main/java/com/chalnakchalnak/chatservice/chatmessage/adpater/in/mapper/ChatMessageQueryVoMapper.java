package com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetMessagesRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageQueryVoMapper {

    public GetMessagesRequestDto toGetMessagesRequestDto(GetMessagesRequestVo getMessagesRequestVo) {
        return GetMessagesRequestDto.builder()
                .chatRoomUuid(getMessagesRequestVo.getChatRoomUuid())
                .lastMessageId(getMessagesRequestVo.getLastMessageId())
                .limit(getMessagesRequestVo.getLimit() != null ? getMessagesRequestVo.getLimit() : 20)
                .build();
    }

    public GetMessagesResponseVo toGetMessagesResponseVo(GetMessagesResponseDto getMessagesResponseDto) {
        return GetMessagesResponseVo.builder()
                .messageId(getMessagesResponseDto.getMessageId())
                .chatRoomUuid(getMessagesResponseDto.getChatRoomUuid())
                .senderUuid(getMessagesResponseDto.getSenderUuid())
                .message(getMessagesResponseDto.getMessage())
                .sentAt(getMessagesResponseDto.getSentAt().toString())
                .build();
    }
}
