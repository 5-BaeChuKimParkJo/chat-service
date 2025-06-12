package com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.validator;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.repository.ChatRoomJpaRepository;
import com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.repository.ChatRoomMemberJpaRepository;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.validator.ChatRoomValidatorPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomValidator implements ChatRoomValidatorPort {

    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatRoomMemberJpaRepository chatRoomMemberJpaRepository;

    @Override
    public void memberAccessed(String chatRoomUuid, String memberUuid) {
        if (!chatRoomJpaRepository.existsByChatRoomUuid(chatRoomUuid)) {
            throw new BaseException(BaseResponseStatus.CHAT_ROOM_NOT_FOUND);
        }
        if (!chatRoomMemberJpaRepository.existsByChatRoomUuidAndMemberUuid(chatRoomUuid, memberUuid)) {
            throw new BaseException(BaseResponseStatus.CHAT_ROOM_MEMBER_NOT_FOUND);
        }
    }
}
