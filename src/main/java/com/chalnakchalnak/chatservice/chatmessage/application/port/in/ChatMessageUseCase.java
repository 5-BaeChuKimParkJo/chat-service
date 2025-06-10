package com.chalnakchalnak.chatservice.chatmessage.application.port.in;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;

public interface ChatMessageUseCase {

    void sendMessage(SendMessageRequestDto sendMessageRequestDto);
}
