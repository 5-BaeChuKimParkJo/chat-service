package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;


public interface ChatMessageRepositoryPort {

    void processMessage(ChatMessageDto chatMessageDto);
    GetMessagesResponseDto findByMessageUuid(String messageUuid);

}
