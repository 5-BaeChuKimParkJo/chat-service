package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.mapper.ChatRoomVoMapper;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in.CreateChatRoomRequestVo;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomUseCase chatRoomUseCase;
    private final ChatRoomVoMapper chatRoomVoMapper;


    @Operation(
            summary = "Create Private ChatRoom API",
            description = "1대1 채팅방 생성 <br>chatRoomType(enum) : AUCTION_PRIVATE(경매 1대1 채팅방), NORMAL_PRIVATE(일반 1대1 채팅방)",
            tags = {"chatroom"}
    )
    @PostMapping("/private")
    public String createPrivateChatRoom(
            @RequestBody @Valid CreateChatRoomRequestVo createChatRoomRequestVo
    ) {

        return chatRoomUseCase.createPrivateChatRoom(
                        chatRoomVoMapper.toCreateChatRoomDto(createChatRoomRequestVo)
        );
    }

}
