package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;

public interface ChatReadCheckPointUpdaterPort {

    Boolean updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto);
}
