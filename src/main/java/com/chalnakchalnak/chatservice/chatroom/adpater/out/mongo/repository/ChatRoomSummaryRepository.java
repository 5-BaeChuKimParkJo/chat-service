package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.document.ChatRoomSummaryDocument;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.mapper.ChatRoomSummaryDocumentMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomSummaryResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomSummaryRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomSummaryRepository implements ChatRoomSummaryRepositoryPort {

    private final ChatRoomSummaryMongoRepository chatRoomSummaryMongoRepository;
    private final ChatRoomSummaryDocumentMapper chatRoomSummaryDocumentMapper;

    @Override
    public List<GetChatRoomSummaryResponseDto> getMyChatRoomList(String memberUuid) {
        List<ChatRoomSummaryDocument> chatRoomSummaryDocuments = chatRoomSummaryMongoRepository.findAllByMemberUuidOrderByLastMessageSentAtDesc(memberUuid);

        return chatRoomSummaryDocuments.stream()
                .map(chatRoomSummaryDocumentMapper::toGetChatRoomSummaryResponseDto)
                .toList();
    }

    @Override
    public void deleteChatRoomSummary(ExitChatRoomRequestDto exitChatRoomRequestDto) {
        chatRoomSummaryMongoRepository.deleteByChatRoomUuidAndMemberUuid(
                exitChatRoomRequestDto.getChatRoomUuid(),
                exitChatRoomRequestDto.getMemberUuid()
        );
    }

    @Override
    public List<GetChatRoomSummaryResponseDto> getMyUnreadCount(String memberUuid) {
        List<ChatRoomSummaryDocument> chatRoomSummaryDocuments = chatRoomSummaryMongoRepository.findAllByMemberUuid(memberUuid);

        return chatRoomSummaryDocuments.stream()
                .map(chatRoomSummaryDocumentMapper::toGetChatRoomSummaryResponseDto)
                .toList();
    }
}
