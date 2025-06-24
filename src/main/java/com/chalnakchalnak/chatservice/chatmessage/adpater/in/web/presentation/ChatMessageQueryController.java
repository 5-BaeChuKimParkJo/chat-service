package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageQueryVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetMessagesRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetReadCheckPointRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseWrapperVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetReadCheckPointResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageQueryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/messages")
@RequiredArgsConstructor
@Slf4j
public class ChatMessageQueryController {

    private final ChatMessageQueryUseCase chatMessageQueryUseCase;
    private final ChatMessageQueryVoMapper chatMessageQueryVoMapper;

    @Operation(
            summary = "Get Messages History API",
            description =
                    "채팅방 메시지 이력 조회 <br>lastMessageUuid : nullable(첫 페이지 조회 시 param에서 삭제) " +
                            "<br>lastMessageSentAt : nullable(첫 페이지 조회 시 param에서 삭제)" +
                            "<br>limit : nullable(default = 20) <br>" +
                            "메시지 타입이 \"REPLY\"인 경우에만 replyToMessageUuid와 replyPreview가 함께 반환됩니다" +
                            "lastMessageUuid와 lastMessageSentAt은 반드시 둘 다 있거나 둘 다 없어야 합니다.",
            tags = {"chat-message"}
    )
    @GetMapping("/history")
    public GetMessagesResponseWrapperVo getMessagesHistory(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @ModelAttribute @Valid GetMessagesRequestVo getMessagesRequestVo
    ) {
        log.info("lastMessageSentAt: {}, lastMessageUuid: {}", getMessagesRequestVo.getLastMessageSentAt(), getMessagesRequestVo.getLastMessageUuid());


        return chatMessageQueryVoMapper.toGetMessagesResponseWrapperVo(
                chatMessageQueryUseCase.getMessages(
                        chatMessageQueryVoMapper.toGetMessagesRequestDto(getMessagesRequestVo, memberUuid)
                )
        );
    }

    @Operation(
            summary = "Get Read Check Point API",
            description = "채팅방 읽음 체크포인트 조회 <br>채팅방 메시지 이력 조회와 함꼐 사용하여 상대방의 메시지 조회 여부를 나타내주세요.",
            tags = {"chat-message"}
    )
    @GetMapping("read-check-point")
    public GetReadCheckPointResponseVo getReadCheckPoint(
            @ModelAttribute @Valid GetReadCheckPointRequestVo getReadCheckPointRequestVo
    ) {
        return chatMessageQueryVoMapper.toGetReadCheckPointResponseVo(
                chatMessageQueryUseCase.getReadCheckPoint(
                        chatMessageQueryVoMapper.toGetReadCheckPointRequestDto(getReadCheckPointRequestVo)
                )
        );
    }

}
