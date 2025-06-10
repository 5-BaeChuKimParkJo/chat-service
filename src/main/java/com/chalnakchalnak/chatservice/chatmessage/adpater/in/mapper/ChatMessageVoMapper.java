package com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChatMessageVoMapper {

    public SendMessageRequestDto toSendMessageRequestDto(SendMessageRequestVo sendMessageRequestVo) {
        return SendMessageRequestDto.builder()
                .chatRoomUuid(sendMessageRequestVo.getChatRoomUuid())
                .senderUuid(sendMessageRequestVo.getSenderUuid())
                .message(sendMessageRequestVo.getMessage())
                .sentAt(LocalDateTime.now())
                .build();
    }
}
