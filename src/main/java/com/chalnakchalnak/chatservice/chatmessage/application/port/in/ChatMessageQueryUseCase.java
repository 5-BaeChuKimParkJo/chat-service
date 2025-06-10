package com.chalnakchalnak.chatservice.chatmessage.application.port.in;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;

import java.util.List;

public interface ChatMessageQueryUseCase {

    List<GetMessagesResponseDto> getMessages(GetMessagesRequestDto getMessagesRequestDto);
}
