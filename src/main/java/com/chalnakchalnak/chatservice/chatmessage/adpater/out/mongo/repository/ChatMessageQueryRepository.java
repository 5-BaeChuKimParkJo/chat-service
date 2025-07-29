package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.document.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.document.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatMessageDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatReadCheckPointDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetReadCheckPointResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageQueryRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberExitRepositoryPort;
import lombok.RequiredArgsConstructor;
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

    private static final LocalDateTime DEFAULT_EXITED_AT = LocalDateTime.of(1970, 1, 1, 0, 0);

    @Override
    public List<GetMessagesResponseDto> getMessages(GetMessagesRequestDto dto) {
        if (dto.getLastMessageSentAt() == null) {
            return getFirstPageMessages(dto);
        }
        return getPagedMessages(dto);
    }

    private List<GetMessagesResponseDto> getFirstPageMessages(GetMessagesRequestDto dto) {
        LocalDateTime exitedAt = chatRoomMemberExitRepositoryPort
                .findByChatRoomUuidAndMemberUuid(dto.getChatRoomUuid(), dto.getMemberUuid())
                .map(exit -> exit.getExitedAt())
                .orElse(DEFAULT_EXITED_AT);

        List<ChatMessageDocument> messages = chatMessageMongoRepository
                .findByChatRoomUuidAndSentAtGreaterThanOrderBySentAtDescMessageUuidDesc(
                        dto.getChatRoomUuid(), exitedAt, PageRequest.of(0, dto.getLimit()));

        return toResponseDtoList(messages);
    }

    private List<GetMessagesResponseDto> getPagedMessages(GetMessagesRequestDto dto) {
        PageRequest pageable = PageRequest.of(0, dto.getLimit());

        List<ChatMessageDocument> messages = chatMessageMongoRepository
                .findByChatRoomUuidAndSentAtLessThanOrderBySentAtDescMessageUuidDesc(
                        dto.getChatRoomUuid(), dto.getLastMessageSentAt(), pageable);

        if (messages.isEmpty()) {
            messages = chatMessageMongoRepository
                    .findByChatRoomUuidAndSentAtAndMessageUuidLessThanOrderBySentAtDescMessageUuidDesc(
                            dto.getChatRoomUuid(), dto.getLastMessageSentAt(), dto.getLastMessageUuid(), pageable);
        }

        return toResponseDtoList(messages);
    }

    private List<GetMessagesResponseDto> toResponseDtoList(List<ChatMessageDocument> messages) {
        return messages.stream()
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
