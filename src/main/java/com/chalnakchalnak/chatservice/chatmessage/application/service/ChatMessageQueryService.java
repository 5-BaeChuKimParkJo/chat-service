package com.chalnakchalnak.chatservice.chatmessage.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetReadCheckPointResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageQueryUseCase;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageQueryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageQueryService implements ChatMessageQueryUseCase {

    private final ChatMessageQueryRepositoryPort chatMessageQueryRepositoryPort;

    @Override
    public List<GetMessagesResponseDto> getMessages(GetMessagesRequestDto getMessagesRequestDto) {
        return chatMessageQueryRepositoryPort.getMessages(getMessagesRequestDto);
    }

    @Override
    public GetReadCheckPointResponseDto getReadCheckPoint(GetReadCheckPointRequestDto getReadCheckPointRequestDto) {
        return chatMessageQueryRepositoryPort.getReadCheckPoint(getReadCheckPointRequestDto);
    }
}
