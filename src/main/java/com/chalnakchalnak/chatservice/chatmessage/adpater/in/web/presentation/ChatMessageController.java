package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.ChatMessageVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.SendMessageRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception.WebSocketErrorMessage;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    private final ChatMessageUseCase chatMessageUseCase;
    private final ChatMessageVoMapper chatMessageVoMapper;

    @MessageMapping("/chat/send")
    public void sendMessage(@Payload @Valid SendMessageRequestVo sendMessageRequestVo) {
        chatMessageUseCase.sendMessage(
                chatMessageVoMapper.toSendMessageRequestDto(sendMessageRequestVo)
        );
    }

}
