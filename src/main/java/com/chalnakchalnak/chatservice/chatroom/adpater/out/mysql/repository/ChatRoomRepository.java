package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomEntity;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.mapper.ChatRoomEntityMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChatRoomRepository implements ChatRoomRepositoryPort {

    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatRoomEntityMapper chatRoomEntityMapper;

    @Override
    public String createChatRoom(CreateChatRoomDto createChatRoomDto) {
        final ChatRoomEntity chatRoom = chatRoomEntityMapper.toChatRoomEntity(createChatRoomDto);
        final ChatRoomEntity savedRoom = chatRoomJpaRepository.save(chatRoom);

        return savedRoom.getChatRoomUuid();
    }

    @Override
    public Optional<String> findRoomUuidById(Long roomId) {
        return chatRoomJpaRepository.findById(roomId).map(ChatRoomEntity::getChatRoomUuid);
    }

}
