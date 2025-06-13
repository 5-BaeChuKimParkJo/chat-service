package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatMessageDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository implements ChatMessageRepositoryPort {

    private final ChatMessageMongoRepository chatMessageMongoRepository;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;

    @Override
    public void save(SendMessageRequestDto sendMessageRequestDto) {
        chatMessageMongoRepository.save(
               chatMessageDocumentMapper.toChatMessageDocument(sendMessageRequestDto)
        );
    }
}
