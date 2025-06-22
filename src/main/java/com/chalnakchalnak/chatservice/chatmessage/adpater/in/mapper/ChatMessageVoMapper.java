package com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.ReadMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ChatMessageVoMapper {

    public SendMessageRequestDto toSendMessageRequestDto(SendMessageRequestVo sendMessageRequestVo) {
        return SendMessageRequestDto.builder()
                .chatRoomUuid(sendMessageRequestVo.getChatRoomUuid())
                .senderUuid(sendMessageRequestVo.getSenderUuid())
                .message(sendMessageRequestVo.getMessage())
                .messageType(sendMessageRequestVo.getMessageType())
                .sentAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .replyToMessageUuid(sendMessageRequestVo.getReplyToMessageUuid())
                .build();
    }

    public ReadMessageRequestDto toReadMessageRequestDto(ReadMessageRequestVo readMessageRequestVo) {
        return ReadMessageRequestDto.builder()
                .chatRoomUuid(readMessageRequestVo.getChatRoomUuid())
                .memberUuid(readMessageRequestVo.getMemberUuid())
                .lastReadMessageSentAt(readMessageRequestVo.getLastReadMessageSentAt())
                .build();
    }
}
