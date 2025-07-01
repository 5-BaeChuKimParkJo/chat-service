package com.chalnakchalnak.chatservice.chatmessage.application.mapper;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class SendMessageMapper {

    public SendMessageRequestDto toSendMessageDtoOfSystem(String chatRoomUuid, String senderUuid, String message) {
        return SendMessageRequestDto.builder()
                .chatRoomUuid(chatRoomUuid)
                .senderUuid(senderUuid)
                .message(message)
                .messageType(MessageType.SYSTEM)
                .sentAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }
}
