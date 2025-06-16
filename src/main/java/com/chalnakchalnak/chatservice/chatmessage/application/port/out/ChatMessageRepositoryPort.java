package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;

public interface ChatMessageRepositoryPort {

    void save(ChatMessageDto chatMessageDto);



}
