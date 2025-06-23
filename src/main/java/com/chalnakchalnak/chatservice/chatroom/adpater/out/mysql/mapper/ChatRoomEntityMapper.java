package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.mapper;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomMemberEntity;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomEntity;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomListByPostResponseDto;
import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomMemberRole;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomEntityMapper {

    public ChatRoomEntity toChatRoomEntity(CreateChatRoomDto createChatRoomDto) {
        return ChatRoomEntity.builder()
                .chatRoomUuid(createChatRoomDto.getChatRoomUuid())
                .postUuid(createChatRoomDto.getPostUuid())
                .chatRoomType(createChatRoomDto.getChatRoomType())
                .build();
    }

    public ChatRoomMemberEntity toChatRoomMemberEntityBySeller (CreateChatRoomMemberDto createChatRoomMemberDto) {
        return ChatRoomMemberEntity.builder()
                .chatRoomUuid(createChatRoomMemberDto.getChatRoomUuid())
                .memberUuid(createChatRoomMemberDto.getSellerUuid())
                .role(ChatRoomMemberRole.SELLER)

                .build();
    }

    public ChatRoomMemberEntity toChatRoomMemberEntityByBuyer (CreateChatRoomMemberDto createChatRoomMemberDto) {
        return ChatRoomMemberEntity.builder()
                .chatRoomUuid(createChatRoomMemberDto.getChatRoomUuid())
                .memberUuid(createChatRoomMemberDto.getBuyerUuid())
                .role(ChatRoomMemberRole.BUYER)
                .build();
    }

    public ChatRoomInfoDto toChatRoomInfoDto(ChatRoomEntity chatRoomEntity) {
        return ChatRoomInfoDto.builder()
                .chatRoomUuid(chatRoomEntity.getChatRoomUuid())
                .postUuid(chatRoomEntity.getPostUuid())
                .chatRoomType(chatRoomEntity.getChatRoomType().toString())
                .build();
    }

    public ChatRoomMemberInfoDto toChatRoomMemberInfoDto(ChatRoomMemberEntity chatRoomMemberEntity) {
        return ChatRoomMemberInfoDto.builder()
                .memberUuid(chatRoomMemberEntity.getMemberUuid())
                .role(chatRoomMemberEntity.getRole().toString())
                .build();
    }

    public GetChatRoomListByPostResponseDto toGetChatRoomListByPostResponseDto(ChatRoomEntity chatRoomEntity) {
        return GetChatRoomListByPostResponseDto.builder()
                .chatRoomUuid(chatRoomEntity.getChatRoomUuid())
                .build();
    }
}
