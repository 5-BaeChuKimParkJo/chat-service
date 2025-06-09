package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.mapper;

import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in.CreateChatRoomRequestVo;
import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomVoMapper {

    public CreateChatRoomRequestDto toCreateChatRoomDto(CreateChatRoomRequestVo createChatRoomRequestVo) {
        return CreateChatRoomRequestDto.builder()
                .postUuid(createChatRoomRequestVo.getPostUuid())
                .sellerUuid(createChatRoomRequestVo.getSellerUuid())
                .buyerUuid(createChatRoomRequestVo.getBuyerUuid())
                .chatRoomType(createChatRoomRequestVo.getChatRoomType())
                .build();
    }
}
