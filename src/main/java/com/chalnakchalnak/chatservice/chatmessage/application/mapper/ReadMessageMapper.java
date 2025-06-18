package com.chalnakchalnak.chatservice.chatmessage.application.mapper;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.ChatRoomSummaryUpdateEvent;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.enums.ChatRoomSummaryUpdateEventType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadMessageMapper {

    public ChatRoomSummaryUpdateEvent toChatRoomSummaryUpdateEventByRead(ReadMessageRequestDto readMessageRequestDto) {
        return ChatRoomSummaryUpdateEvent.builder()
                .chatRoomUuid(readMessageRequestDto.getChatRoomUuid())
                .targetMemberUuids(List.of(
                        readMessageRequestDto.getMemberUuid()
                ))
                .eventType(ChatRoomSummaryUpdateEventType.READ_UPDATE.toString())
                .build();
    }
}
