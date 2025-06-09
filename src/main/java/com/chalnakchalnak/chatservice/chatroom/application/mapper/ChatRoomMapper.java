package com.chalnakchalnak.chatservice.chatroom.application.mapper;

import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomMemberRole;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomMapper {

    public CreateChatRoomDto toCreateChatRoomDto(CreateChatRoomRequestDto createChatRoomRequestDto, String chatRoomUuid) {
        return CreateChatRoomDto.builder()
                .chatRoomUuid(chatRoomUuid)
                .postUuid(createChatRoomRequestDto.getPostUuid())
                .chatRoomType(createChatRoomRequestDto.getChatRoomType())
                .build();
    }

    public CreateChatRoomMemberDto toCreateChatRoomMemberDto(CreateChatRoomRequestDto createChatRoomRequestDto, String chatRoomUuid) {
        return CreateChatRoomMemberDto.builder()
                .chatRoomUuid(chatRoomUuid)
                .sellerUuid(createChatRoomRequestDto.getSellerUuid())
                .buyerUuid(createChatRoomRequestDto.getBuyerUuid())
                .build();
    }
}
