package com.chalnakchalnak.chatservice.chatmessage.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.UpdateReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatReadCheckPointRepositoryPort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatMessageService implements ChatMessageUseCase {

    private final PublishChatMessagePort publishChatMessagePort;
    private final ChatMessageRepositoryPort chatMessageRepositoryPort;
    private final ChatReadCheckPointRepositoryPort chatReadCheckPointRepositoryPort;

    @Override
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {
        chatMessageRepositoryPort.save(sendMessageRequestDto);
        publishChatMessagePort.publishChatMessage(sendMessageRequestDto);
    }

    @Override
    public void updateReadCheckPoint(UpdateReadCheckPointRequestDto updateReadCheckPointRequestDto) {
        chatReadCheckPointRepositoryPort.updateReadCheckPoint(updateReadCheckPointRequestDto);
    }
}
