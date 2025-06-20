package com.chalnakchalnak.chatservice.chatroom.application.service;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.mapper.ChatRoomMapper;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.*;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService implements ChatRoomUseCase {

    private final ChatRoomRepositoryPort chatRoomRepositoryPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final ChatRoomSummaryRepositoryPort chatRoomSummaryRepositoryPort;
    private final ChatRoomMemberExitUpdaterPort chatRoomMemberExitUpdaterPort;
    private final ChatRoomMapper chatRoomMapper;
    private final GenerateUuidPort generateUuidPort;

    @Override
    @Transactional
    public String createPrivateChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto) {
        Optional<Long> existingRoomId = chatRoomMemberRepositoryPort.findPrivateChatRoomUuid(
                createChatRoomRequestDto.getPostUuid(), createChatRoomRequestDto.getBuyerUuid()
        );

        if (existingRoomId.isPresent()) {
            return chatRoomRepositoryPort.findRoomUuidById(existingRoomId.get())
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.CHAT_ROOM_NOT_FOUND));
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

    //@Transactional
    @Override
    public void exitChatRoom(ExitChatRoomRequestDto exitChatRoomRequestDto) {

        chatRoomMemberExitUpdaterPort.updateExitedAt(exitChatRoomRequestDto);

        chatRoomSummaryRepositoryPort.deleteChatRoomSummary(exitChatRoomRequestDto);
    }

}
