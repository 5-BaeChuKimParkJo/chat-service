package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetReadCheckPointResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ChatReadCheckPointDocumentMapper {
    public GetReadCheckPointResponseDto toGetReadCheckPointResponseDto(String lastReadMessageSentAt) {
        return GetReadCheckPointResponseDto.builder()
                .lastReadMessageSentAt(lastReadMessageSentAt)
                .build();
    }
}
