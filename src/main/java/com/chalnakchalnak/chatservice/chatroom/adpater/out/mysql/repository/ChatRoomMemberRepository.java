package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomMemberEntity;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.mapper.ChatRoomEntityMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChatRoomMemberRepository implements ChatRoomMemberRepositoryPort {

    private final ChatRoomMemberJpaRepository chatRoomMemberJpaRepository;
    private final ChatRoomEntityMapper chatRoomEntityMapper;

    @Override
    public void saveChatRoomMembers(CreateChatRoomMemberDto createChatRoomMemberDto) {
        final ChatRoomMemberEntity seller = chatRoomEntityMapper.toChatRoomMemberEntityBySeller(createChatRoomMemberDto);
        final ChatRoomMemberEntity buyer = chatRoomEntityMapper.toChatRoomMemberEntityByBuyer(createChatRoomMemberDto);

        chatRoomMemberJpaRepository.saveAll(List.of(seller, buyer));
    }

    @Override
    public Optional<Long> findPrivateChatRoomUuid(String postUuid, String buyerUuid) {
        return chatRoomMemberJpaRepository.findPrivateRoomId(postUuid, buyerUuid);
    }

    @Override
    public String findOpponentUuid(String chatRoomUuid, String myMemberUuid) {
        List<ChatRoomMemberEntity> members = chatRoomMemberJpaRepository.findByChatRoomUuid(chatRoomUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CHAT_ROOM_NOT_FOUND));

        return members.stream()
                .filter(member -> !member.getMemberUuid().equals(myMemberUuid))
                .findFirst()
                .map(ChatRoomMemberEntity::getMemberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CHAT_ROOM_MEMBER_NOT_FOUND));
    }
}
