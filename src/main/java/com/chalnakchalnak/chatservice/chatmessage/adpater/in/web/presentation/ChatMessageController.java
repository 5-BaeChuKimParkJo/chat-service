package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.ReadMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageUseCase chatMessageUseCase;
    private final ChatMessageVoMapper chatMessageVoMapper;

    @MessageMapping("/chat/send")
    public void sendMessage(@Payload @Valid SendMessageRequestVo sendMessageRequestVo) {
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