package com.chalnakchalnak.chatservice.chatmessage.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements ChatMessageUseCase {

    private final PublishChatMessagePort publishChatMessagePort;
    private final ChatMessageRepositoryPort chatMessageRepositoryPort;

    @Override
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {
        Boolean result = publishChatMessagePort.publishChatMessage(sendMessageRequestDto);

        if (!result) {
            throw new BaseException(BaseResponseStatus.FAILED_PUBLISH_MESSAGE);
        }
    }

}
