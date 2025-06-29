package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;

import java.util.List;

public interface ChatRoomSummaryUpdaterPort {

void bulkUpdateOnMessages(List<ChatMessageDto> chatMessageDtoList, List<String> receiverUuids);
    void updateOnRead(ReadMessageRequestDto readMessageRequestDto);
}
