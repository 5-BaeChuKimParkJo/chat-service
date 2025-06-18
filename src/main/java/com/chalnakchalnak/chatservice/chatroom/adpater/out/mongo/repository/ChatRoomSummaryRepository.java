package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.mapper.ChatRoomSummaryDocumentMapper;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.ChatRoomSummaryUpdateEvent;
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
        return chatRoomSummaryMongoRepository.findAllByMemberUuidOrderByLastMessageSentAtDesc(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_CHAT_ROOM_NOT_FOUND))
                .stream()
                .map(chatRoomSummaryDocumentMapper::toGetChatRoomSummaryResponseDto)
                .toList();
    }
}
