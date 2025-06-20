package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageQueryVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetMessagesRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetReadCheckPointRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetReadCheckPointResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository.ChatMessageMongoRepository;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository.ChatMessageQueryRepository;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageQueryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/messages")
@RequiredArgsConstructor
public class ChatMessageQueryController {

    private final ChatMessageQueryUseCase chatMessageQueryUseCase;
    private final ChatMessageQueryVoMapper chatMessageQueryVoMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Operation(
            summary = "Get Messages History API",
            description = "채팅방 메시지 이력 조회 <br>lastMessageId : nullable(첫 페이지 조회 시 null 입력) <br>limit : nullable(default = 20)",
            tags = {"chat-message"}
    )
    @GetMapping("/history")
    public List<GetMessagesResponseVo> getMessagesHistory(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @ModelAttribute @Valid GetMessagesRequestVo getMessagesRequestVo
    ) {
        return chatMessageQueryUseCase.getMessages(
                chatMessageQueryVoMapper.toGetMessagesRequestDto(getMessagesRequestVo, memberUuid)
                ).stream()
                .map(chatMessageQueryVoMapper::toGetMessagesResponseVo)
                .toList();
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
