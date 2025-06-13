package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatMessageDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageQueryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatMessageQueryRepository implements ChatMessageQueryRepositoryPort {

    private final ChatMessageMongoRepository chatMessageMongoRepository;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;

    @Override
    public List<GetMessagesResponseDto> getMessages(GetMessagesRequestDto getMessagesRequestDto) {
        final PageRequest pageable = PageRequest.of(0, getMessagesRequestDto.getLimit());
        List<ChatMessageDocument> messages;

        if (getMessagesRequestDto.getLastMessageId() == null) {
            messages = chatMessageMongoRepository.findTopByChatRoomUuidOrderByIdDesc(getMessagesRequestDto.getChatRoomUuid(), pageable);
        } else {
            final ObjectId objectId = new ObjectId(getMessagesRequestDto.getLastMessageId());
            messages = chatMessageMongoRepository.findByChatRoomUuidAndIdLessThanOrderByIdDesc(
                    getMessagesRequestDto.getChatRoomUuid(), objectId, pageable);
        }

        return messages
                .stream()
                .map(chatMessageDocumentMapper::toGetMessagesResponseDto)
                .toList();
    }
}
