package com.chalnakchalnak.chatservice.chatroom.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.mapper.SendMessageMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomInfoRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomListByPostRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomInfoResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomListByPostResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.mapper.ChatRoomMapper;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.*;
import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService implements ChatRoomUseCase {

    private final ChatRoomRepositoryPort chatRoomRepositoryPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final ChatRoomSummaryRepositoryPort chatRoomSummaryRepositoryPort;
    private final ChatRoomMemberExitUpdaterPort chatRoomMemberExitUpdaterPort;
    private final PublishChatMessagePort publishChatMessagePort;
    private final SendMessageMapper sendMessageMapper;
    private final ChatRoomMapper chatRoomMapper;
    private final GenerateUuidPort generateUuidPort;

    private final String AUCTION_CHATROOM_SYSTEM_MESSAGE = "낙찰되었습니다. 거래가 완료되면 거래완료 버튼을 눌러주세요.";

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

        if(createChatRoomRequestDto.getChatRoomType() == ChatRoomType.AUCTION_PRIVATE) {
            publishChatMessagePort.publishChatMessage(
                    sendMessageMapper.toSendMessageDtoOfSystem(
                            chatRoomUuid, createChatRoomRequestDto.getSellerUuid(), AUCTION_CHATROOM_SYSTEM_MESSAGE
                    )
            );
        }

        return chatRoomUuid;
    }

    @Transactional
    @Override
    public void exitChatRoom(ExitChatRoomRequestDto exitChatRoomRequestDto) {

        chatRoomMemberExitUpdaterPort.updateExitedAt(exitChatRoomRequestDto);

        chatRoomSummaryRepositoryPort.deleteChatRoomSummary(exitChatRoomRequestDto);
    }

    @Override
    public GetChatRoomInfoResponseDto getChatRoomInfo(GetChatRoomInfoRequestDto getChatRoomInfoRequestDto) {
        ChatRoomInfoDto chatRoomInfoDto =
                chatRoomRepositoryPort.getChatRoomInfo(getChatRoomInfoRequestDto)
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.CHAT_ROOM_NOT_FOUND));

        List<ChatRoomMemberInfoDto> chatRoomMemberInfoDto = chatRoomMemberRepositoryPort.getChatRoomMembers(getChatRoomInfoRequestDto);

        return chatRoomMapper.toGetChatRoomInfoResponseDto(chatRoomInfoDto, chatRoomMemberInfoDto);
    }

    @Override
    public List<GetChatRoomListByPostResponseDto> getChatRoomListByPost(GetChatRoomListByPostRequestDto getChatRoomListByPostRequestDto) {
        return chatRoomRepositoryPort.getChatRoomListByPost(getChatRoomListByPostRequestDto);

    }

}
