package com.chalnakchalnak.chatservice.chatmessage.application.mapper;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.ChatRoomSummaryUpdateEvent;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.enums.ChatRoomSummaryUpdateEventType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatMessageMapper {

    public ChatRoomSummaryUpdateEvent toChatRoomSummaryUpdateEventByMessage(
            ChatMessageDto chatMessageDto,
            String receiverUuid
    ) {
        return ChatRoomSummaryUpdateEvent.builder()
                .chatRoomUuid(chatMessageDto.getChatRoomUuid())
                .targetMemberUuids(List.of(
                        chatMessageDto.getSenderUuid(),
                        receiverUuid
                ))
                .eventType(ChatRoomSummaryUpdateEventType.MESSAGE_UPDATE.toString())
                .build();
    }
}
