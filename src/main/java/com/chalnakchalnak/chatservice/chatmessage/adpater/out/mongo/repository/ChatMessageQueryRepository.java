package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatMessageDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatReadCheckPointDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetReadCheckPointResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageQueryRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberExitRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatMessageQueryRepository implements ChatMessageQueryRepositoryPort {

    private final ChatMessageMongoRepository chatMessageMongoRepository;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;
    private final ChatReadCheckPointMongoRepository chatReadCheckPointMongoRepository;
    private final ChatRoomMemberExitRepositoryPort chatRoomMemberExitRepositoryPort;
    private final ChatReadCheckPointDocumentMapper chatReadCheckPointDocumentMapper;

    @Override
    public List<GetMessagesResponseDto> getMessages(GetMessagesRequestDto getMessagesRequestDto) {
        final PageRequest pageable = PageRequest.of(0, getMessagesRequestDto.getLimit());
        List<ChatMessageDocument> messages;

        if (getMessagesRequestDto.getLastMessageId() == null) {
            final LocalDateTime exitedAt = chatRoomMemberExitRepositoryPort
                    .findByChatRoomUuidAndMemberUuid(getMessagesRequestDto.getChatRoomUuid(), getMessagesRequestDto.getMemberUuid())
                    .map(chatRoomMemberExitDto -> chatRoomMemberExitDto.getExitedAt())
                    .orElse(LocalDateTime.of(1970, 1, 1, 0, 0));

            messages = chatMessageMongoRepository.findByChatRoomUuidAndSentAtAfterOrderByIdDesc(
                    getMessagesRequestDto.getChatRoomUuid(), exitedAt, pageable);
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

    @Override
    public GetReadCheckPointResponseDto getReadCheckPoint(GetReadCheckPointRequestDto getReadCheckPointRequestDto) {
        final String lastReadMessageSentAt =
                chatReadCheckPointMongoRepository
                        .findByChatRoomUuidAndMemberUuid(
                                getReadCheckPointRequestDto.getChatRoomUuid(),
                                getReadCheckPointRequestDto.getMemberUuid()
                        )
                        .map(ChatReadCheckPointDocument::getLastReadMessageSentAt)
                        .orElse(LocalDateTime.of(1970, 1, 1, 0, 0))
                        .toString();


        return chatReadCheckPointDocumentMapper.toGetReadCheckPointResponseDto(lastReadMessageSentAt);
    }
}
