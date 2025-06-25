package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.ReadMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    private final ChatMessageUseCase chatMessageUseCase;
    private final ChatMessageVoMapper chatMessageVoMapper;
    private final StringRedisTemplate redisTemplate;

    String startKey = "chat:perf:start-time";
    String requestCountKey = "chat:perf:total-request-count";
    @MessageMapping("/chat/send")
    public void sendMessage(@Payload @Valid SendMessageRequestVo sendMessageRequestVo) {

        if (!redisTemplate.hasKey(startKey)) {
            redisTemplate.opsForValue().set(startKey, String.valueOf(System.currentTimeMillis()));
        }

        // 요청 카운트 증가
        redisTemplate.opsForValue().increment(requestCountKey);


        chatMessageUseCase.sendMessage(
                chatMessageVoMapper.toSendMessageRequestDto(sendMessageRequestVo)
        );
    }

    @MessageMapping("/chat/read")
    public void updateReadCheckPoint(@Payload ReadMessageRequestVo readMessageRequestVo) {
        chatMessageUseCase.updateReadCheckPoint(
                chatMessageVoMapper.toReadMessageRequestDto(readMessageRequestVo)
        );
    }


}