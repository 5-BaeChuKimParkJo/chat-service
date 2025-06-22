package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomEntity;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.mapper.ChatRoomEntityMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomInfoRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomListByPostRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomListByPostResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public Optional<ChatRoomInfoDto> getChatRoomInfo(GetChatRoomInfoRequestDto getChatRoomInfoRequestDto) {
        return chatRoomJpaRepository.findByChatRoomUuid(getChatRoomInfoRequestDto.getChatRoomUuid())
                .map(chatRoomEntityMapper::toChatRoomInfoDto);
    }

    @Override
    public Optional<List<GetChatRoomListByPostResponseDto>> getChatRoomListByPost(GetChatRoomListByPostRequestDto getChatRoomListByPostRequestDto) {
        return chatRoomJpaRepository.findByPostUuid(getChatRoomListByPostRequestDto.getPostUuid())
                .map(entity ->
                        entity.stream()
                                .map(chatRoomEntityMapper::toGetChatRoomListByPostResponseDto)
                                .toList()
                );
    }

}
