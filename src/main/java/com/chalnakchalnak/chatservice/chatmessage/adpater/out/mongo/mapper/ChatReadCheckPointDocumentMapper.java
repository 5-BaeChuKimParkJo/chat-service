package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChatReadCheckPointDocumentMapper {

    public ChatReadCheckPointDocument toBaseDocument(
            ReadMessageRequestDto readMessageRequestDto,
            LocalDateTime sentAt,
            LocalDateTime now
    ) {
        return ChatReadCheckPointDocument.builder()
                .chatRoomUuid(readMessageRequestDto.getChatRoomUuid())
                .memberUuid(readMessageRequestDto.getMemberUuid())
                .lastReadMessageSentAt(sentAt)
                .updatedAt(now)
                .build();
    }
}
