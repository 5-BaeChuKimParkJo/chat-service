package com.chalnakchalnak.chatservice.chatroom.application.service;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.CreateChatRoomResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.mapper.ChatRoomMapper;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.GenerateUuidPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService implements ChatRoomUseCase {

    private final ChatRoomRepositoryPort chatRoomRepositoryPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final ChatRoomMapper chatRoomMapper;
    private final GenerateUuidPort generateUuidPort;

    @Override
    @Transactional
    public String createPrivateChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto) {
        Optional<Long> existingRoomId = chatRoomMemberRepositoryPort.findPrivateRoomId(
                createChatRoomRequestDto.getPostUuid(), createChatRoomRequestDto.getBuyerUuid()
        );

        if (existingRoomId.isPresent()) {
            return chatRoomRepositoryPort.findRoomUuidById(existingRoomId.get())
                    .orElseThrow(() -> new IllegalStateException("Room not found"));
        }

        final String chatRoomUuid = generateUuidPort.generateUuid();

        chatRoomRepositoryPort.createChatRoom(
                chatRoomMapper.toCreateChatRoomDto(createChatRoomRequestDto, chatRoomUuid)
        );
        chatRoomMemberRepositoryPort.saveChatRoomMembers(
                chatRoomMapper.toCreateChatRoomMemberDto(createChatRoomRequestDto, chatRoomUuid)
        );

        return chatRoomUuid;
    }

}
