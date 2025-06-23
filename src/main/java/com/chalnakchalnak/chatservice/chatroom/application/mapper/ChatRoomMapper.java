package com.chalnakchalnak.chatservice.chatroom.application.mapper;

import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.ChatRoomMemberInfoDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.CreateChatRoomMemberDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomInfoResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public GetChatRoomInfoResponseDto toGetChatRoomInfoResponseDto(
            ChatRoomInfoDto chatRoomInfoDto,
            List<ChatRoomMemberInfoDto> chatRoomMemberInfoDto
    ) {
        return GetChatRoomInfoResponseDto.builder()
                .chatRoomUuid(chatRoomInfoDto.getChatRoomUuid())
                .postUuid(chatRoomInfoDto.getPostUuid())
                .chatRoomType(chatRoomInfoDto.getChatRoomType().toString())
                .members(chatRoomMemberInfoDto)
                .build();
    }
}
