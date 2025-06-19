package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;

public interface ImageKeyValidatorPort {

    void validateImageMessage(SendMessageRequestDto sendMessageRequestDto);
}
