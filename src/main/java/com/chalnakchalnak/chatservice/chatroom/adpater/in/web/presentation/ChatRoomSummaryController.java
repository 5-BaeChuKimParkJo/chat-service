package com.chalnakchalnak.chatservice.chatroom.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.mapper.ChatRoomSummaryVoMapper;
import com.chalnakchalnak.chatservice.chatroom.adpater.in.web.vo.out.GetChatRoomSummaryResponseVo;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomSummaryUseCase;
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

    @GetMapping
    public List<GetChatRoomSummaryResponseVo> getMyChatRoomList(
            @RequestHeader("MEMBER-UUID") String memberUuid
    ) {
        return chatRoomSummaryUseCase.getMyChatRoomList(memberUuid)
                .stream()
                .map(chatRoomSummaryVoMapper::toGetChatRoomSummaryResponseVo)
                .toList();
    }


}
