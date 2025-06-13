package com.chalnakchalnak.chatservice.chatmessage.application.port.in;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.UpdateReadCheckPointRequestDto;

public interface ChatMessageUseCase {

    void sendMessage(SendMessageRequestDto sendMessageRequestDto);

    void updateReadCheckPoint(UpdateReadCheckPointRequestDto updateReadCheckPointRequestDto);
}
