package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;

import java.util.List;


public interface ChatMessageRepositoryPort {

    void processMessage(ChatMessageDto chatMessageDto);
    void bulkSaveMessages(List<ChatMessageDto> messages);
    GetMessagesResponseDto findByMessageUuid(String messageUuid);

}
