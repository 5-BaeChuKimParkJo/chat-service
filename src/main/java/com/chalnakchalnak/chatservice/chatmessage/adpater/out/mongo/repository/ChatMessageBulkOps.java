package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatMessageDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatMessageBulkOps {

    private final MongoTemplate mongoTemplate;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;

    public void saveMessages(List<ChatMessageDto> messages) {
        BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, "chat_message");

        bulkOps.insert(
                messages.stream()
                        .map(chatMessageDocumentMapper::toChatMessageDocument)
                        .collect(Collectors.toList())
        );

        bulkOps.execute();

    }
}
