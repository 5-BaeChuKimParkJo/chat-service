package com.chalnakchalnak.chatservice.chatmessage.application.port.in;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;

public interface ChatMessageUseCase {

    void sendMessage(SendMessageRequestDto sendMessageRequestDto);
    void updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto);
}
