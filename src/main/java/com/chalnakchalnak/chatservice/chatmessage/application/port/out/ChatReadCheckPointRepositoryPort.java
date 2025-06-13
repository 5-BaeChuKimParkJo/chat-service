package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.UpdateReadCheckPointRequestDto;

public interface ChatReadCheckPointRepositoryPort {

    void updateReadCheckPoint(UpdateReadCheckPointRequestDto updateReadCheckPointRequestDto);
}
