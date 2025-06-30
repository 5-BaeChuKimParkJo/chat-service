package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.mapper.ChatRoomVoMapper;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in.CreateChatRoomRequestVo;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in.GetChatRoomInfoRequestVo;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.in.GetChatRoomListByPostRequestVo;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.out.GetChatRoomListByPostResponseVo;
import com.chalnakchalnak.chatservice.chatroom.application.dto.out.GetChatRoomInfoResponseDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomUseCase chatRoomUseCase;
    private final ChatRoomVoMapper chatRoomVoMapper;


    @Operation(
            summary = "Create Private ChatRoom API",
            description = "1대1 채팅방 생성 <br>chatRoomType(enum) : AUCTION_PRIVATE(경매 1대1 채팅방), NORMAL_PRIVATE(일반 1대1 채팅방)" +
                    "<br>경매 채팅방 생성 시, SYSTEM MESSAGE가 생성됩니다.",
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

    @Operation(
            summary = "Exit ChatRoom API",
            description = "채팅방 나가기 API <br>채팅방에서 나가도 채팅방이 삭제되지는 않음 <br>나간 멤버의 채팅방 리스트와 이전까지의 메시지 조회가 불가하도록 적용됩니다.",
            tags = {"chatroom"}
    )
    @PostMapping("/exit/{chatRoomUuid}")
    public void exitChatRoom(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @PathVariable String chatRoomUuid
    ) {

        chatRoomUseCase.exitChatRoom(
                chatRoomVoMapper.toExitChatRooRequestDto(memberUuid, chatRoomUuid)
        );
    }

    @GetMapping("/info/{chatRoomUuid}")
    @Operation(
            summary = "Get ChatRoom Info API",
            description = "채팅방 정보 조회 API",
            tags = {"chatroom"}
    )
    public GetChatRoomInfoResponseDto getChatRoomInfo(
            @ModelAttribute @Valid GetChatRoomInfoRequestVo getChatRoomInfoRequestVo
    ) {

        return chatRoomUseCase.getChatRoomInfo(
                chatRoomVoMapper.toGetChatRoomInfoDto(getChatRoomInfoRequestVo)
        );
    }

    @GetMapping("/{postUuid}")
    @Operation(
            summary = "Get ChatRoom List By Post API",
            description = "특정 게시글에 대한 채팅방 정보 조회 API <br>해당 게시글에 대한 1대1 채팅방이 존재하지 않는 경우, 빈 값 반환",
            tags = {"chatroom"}
    )
    public List<GetChatRoomListByPostResponseVo> getChatRoomListByPost(
            @ModelAttribute @Valid GetChatRoomListByPostRequestVo getChatRoomListByPostRequestVo
    ) {

        return chatRoomUseCase.getChatRoomListByPost(
                chatRoomVoMapper.toGetChatRoomListByPostRequestDto(getChatRoomListByPostRequestVo))
                .stream()
                .map(chatRoomVoMapper::toGetChatRoomListByPostResponseVo)
                .toList();
    }

}
