package com.chalnakchalnak.chatservice.chatroom.application.port.out.validator;

public interface ChatRoomValidatorPort {

    void memberAccessed(String roomUUid, String userUuid);
}
