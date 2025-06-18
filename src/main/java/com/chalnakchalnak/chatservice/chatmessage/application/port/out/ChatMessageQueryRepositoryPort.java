package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetReadCheckPointResponseDto;

import java.util.List;

public interface ChatMessageQueryRepositoryPort {

    List<GetMessagesResponseDto> getMessages(GetMessagesRequestDto getMessagesRequestDto);
    GetReadCheckPointResponseDto getReadCheckPoint(GetReadCheckPointRequestDto getReadCheckPointRequestDto);
}
