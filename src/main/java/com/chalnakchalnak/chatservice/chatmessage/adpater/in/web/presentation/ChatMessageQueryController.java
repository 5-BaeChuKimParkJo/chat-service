package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageQueryVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetMessagesRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository.ChatMessageMongoRepository;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository.ChatMessageQueryRepository;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageQueryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/messages")
@RequiredArgsConstructor
public class ChatMessageQueryController {

    private final ChatMessageQueryUseCase chatMessageQueryUseCase;
    private final ChatMessageQueryVoMapper chatMessageQueryVoMapper;

    @Operation(
            summary = "Get Messages History API",
            description = "채팅방 메시지 이력 조회 <br>lastMessageId : nullable <br>limit : nullable(default = 20)",
            tags = {"chat"}
    )
    @GetMapping("/history")
    public List<GetMessagesResponseVo> getMessagesHistory(
            @ModelAttribute @Valid GetMessagesRequestVo getMessagesRequestVo
    ) {
        return chatMessageQueryUseCase.getMessages(
                chatMessageQueryVoMapper.toGetMessagesRequestDto(getMessagesRequestVo)
                ).stream()
                .map(chatMessageQueryVoMapper::toGetMessagesResponseVo)
                .toList();
    }

    @GetMapping("read-check-point")
    public GetReadCheckPointResponseVo

}
