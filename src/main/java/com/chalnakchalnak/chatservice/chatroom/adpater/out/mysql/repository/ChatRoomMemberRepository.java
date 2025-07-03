package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomMemberEntity;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.mapper.ChatRoomEntityMapper;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.GetChatRoomInfoRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<ChatRoomMemberEntity> members = chatRoomMemberJpaRepository.findByChatRoomUuid(chatRoomUuid);
        if (members.isEmpty()) {
            throw new BaseException(BaseResponseStatus.CHAT_ROOM_MEMBER_NOT_FOUND);
        }

        return members.stream()
                .filter(member -> !member.getMemberUuid().equals(myMemberUuid))
                .findFirst()
                .map(ChatRoomMemberEntity::getMemberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CHAT_ROOM_MEMBER_NOT_FOUND));
    }

    @Override
    public List<ChatRoomMemberInfoDto> getChatRoomMembers(GetChatRoomInfoRequestDto getChatRoomInfoRequestDto) {
        List<ChatRoomMemberEntity> chatRoomMemberEntities =
                chatRoomMemberJpaRepository.findByChatRoomUuid(getChatRoomInfoRequestDto.getChatRoomUuid());
        if (chatRoomMemberEntities.isEmpty()) {
            throw new BaseException(BaseResponseStatus.CHAT_ROOM_MEMBER_NOT_FOUND);
        }

        return chatRoomMemberEntities.stream()
                .map(chatRoomEntityMapper::toChatRoomMemberInfoDto)
                .toList();
    }

    @Override
    public Map<String, Map<String, String>> findAllOpponentUuids(List<String> chatRoomUuids) {
        List<ChatRoomMemberEntity> members = chatRoomMemberJpaRepository.findByChatRoomUuidIn(chatRoomUuids);

        Map<String, Map<String, String>> result = new HashMap<>();

        for (ChatRoomMemberEntity member : members) {
            result.computeIfAbsent(member.getChatRoomUuid(), k -> new HashMap<>())
                    .put(member.getMemberUuid(), null);
        }

        for (ChatRoomMemberEntity member : members) {
            Map<String, String> roomMap = result.get(member.getChatRoomUuid());
            for (String senderUuid : roomMap.keySet()) {
                if (!senderUuid.equals(member.getMemberUuid())) {
                    roomMap.put(senderUuid, member.getMemberUuid());
                }
            }
        }

        return result;
    }
}
