package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;

public interface ChatRoomSummaryUpdaterPort {

    void updateOnMessage(ChatMessageDto chatMessageDto, String receiverUuid);
    void updateOnRead(ReadMessageRequestDto readMessageRequestDto);
}
