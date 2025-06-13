package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.UpdateReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

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
    public void markAsRead(UpdateReadCheckPointRequestDto message) {
        chatMessageUseCase.updateReadCheckPoint(message);
    }

}
