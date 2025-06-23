package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.mapper.ChatRoomSummaryVoMapper;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.out.GetChatRoomSummaryResponseVo;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomSummaryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatroom-summary")
@RequiredArgsConstructor
public class ChatRoomSummaryController {

    private final ChatRoomSummaryUseCase chatRoomSummaryUseCase;
    private final ChatRoomSummaryVoMapper chatRoomSummaryVoMapper;

    @Operation(summary = "Get My ChatRoomSummaryList",
               description = "내가 참여하고 있는 채팅방의 요약 정보를 조회합니다.", tags = {"chatroom-summary"})
    @GetMapping
    public List<GetChatRoomSummaryResponseVo> getMyChatRoomList(
            @RequestHeader("X-Member-Uuid") String memberUuid
    ) {
        return chatRoomSummaryUseCase.getMyChatRoomList(memberUuid)
                .stream()
                .map(chatRoomSummaryVoMapper::toGetChatRoomSummaryResponseVo)
                .toList();
    }


}
