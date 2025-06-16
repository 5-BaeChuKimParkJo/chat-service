package com.chalnakchalnak.chatservice.chatmessage.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatReadCheckPointRepositoryPort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.SendMessageToClientPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ChatMessageService implements ChatMessageUseCase {

    private final PublishChatMessagePort publishChatMessagePort;
    private final ChatReadCheckPointRepositoryPort chatReadCheckPointRepositoryPort;
    private final SendMessageToClientPort sendMessageToClientPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;

    @Override
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {
        Boolean result = publishChatMessagePort.publishChatMessage(sendMessageRequestDto);

        if (!result) {
            throw new BaseException(BaseResponseStatus.FAILED_PUBLISH_MESSAGE);
        }
    }

    @Transactional
    @Override
    public void updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto) {
        chatReadCheckPointRepositoryPort.updateReadCheckPoint(readMessageRequestDto);

        final String opponentUuid = chatRoomMemberRepositoryPort.findByChatRoomUuidAndMyMemberUuid(
                readMessageRequestDto.getChatRoomUuid(), readMessageRequestDto.getMemberUuid()
        );

        sendMessageToClientPort.sendMessage(readMessageRequestDto, opponentUuid);
    }
}
