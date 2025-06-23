package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.mapper.ChatRoomMemberExitDocumentMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberExitDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberExitRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRoomMemberExitRepository implements ChatRoomMemberExitRepositoryPort {

    private final ChatRoomMemberExitMongoRepository chatRoomMemberExitMongoRepository;
    private final ChatRoomMemberExitDocumentMapper chatRoomMemberExitDocumentMapper;

    @Override
    public Optional<ChatRoomMemberExitDto> findByChatRoomUuidAndMemberUuid(String chatRoomUuid, String memberUuid) {
        return chatRoomMemberExitMongoRepository.findByChatRoomUuidAndMemberUuid(chatRoomUuid, memberUuid)
                .map(chatRoomMemberExitDocumentMapper::toChatRoomMemberExitDto);
    }

}
