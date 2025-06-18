package com.chalnakchalnak.chatservice.chatroom.application.port.out;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.payload.ChatRoomSummaryUpdateEvent;

public interface PublishChatRoomSummaryUpdatePort {

    void publishChatRoomSummaryUpdate(ChatRoomSummaryUpdateEvent chatRoomSummaryUpdateEvent);

}
