package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.UpdateReadCheckPointRequestDto;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChatReadCheckPointDocumentMapper {

    public ChatReadCheckPointDocument toBaseDocument(
            UpdateReadCheckPointRequestDto updateReadCheckPointRequestDto,
            ObjectId objectId,
            LocalDateTime now
    ) {
        return ChatReadCheckPointDocument.builder()
                .chatRoomUuid(updateReadCheckPointRequestDto.getChatRoomUuid())
                .memberUuid(updateReadCheckPointRequestDto.getMemberUuid())
                .lastReadMessageId(objectId)
                .updatedAt(now)
                .build();
    }
}
