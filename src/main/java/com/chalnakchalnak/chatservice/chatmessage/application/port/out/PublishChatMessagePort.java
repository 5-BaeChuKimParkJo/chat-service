package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;

public interface PublishChatMessagePort {

    Boolean publishChatMessage(SendMessageRequestDto sendMessageRequestDto);
}
