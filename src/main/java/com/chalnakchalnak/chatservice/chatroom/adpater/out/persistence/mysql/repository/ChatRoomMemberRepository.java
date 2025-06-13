package com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.entity.ChatRoomMemberEntity;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.mapper.ChatRoomEntityMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
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
    public Optional<Long> findPrivateRoomId(String postUuid, String buyerUuid) {
        return chatRoomMemberJpaRepository.findPrivateRoomId(postUuid, buyerUuid);
    }
}
