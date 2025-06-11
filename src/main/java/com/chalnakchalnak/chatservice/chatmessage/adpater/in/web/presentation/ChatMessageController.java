package com.chalnakchalnak.chatservice.chatmessage.adpater.in.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
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
    public void sendMessage(@Payload SendMessageRequestVo sendMessageRequestVo) {
        chatMessageUseCase.sendMessage(
                chatMessageVoMapper.toSendMessageRequestDto(sendMessageRequestVo)
        );
    }

}
