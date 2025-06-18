package com.chalnakchalnak.chatservice.chatmessage.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.mapper.ReadMessageMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.ChatMessageUseCase;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatReadCheckPointUpdaterPort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.PublishChatMessagePort;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.SendMessageToClientPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomSummaryUpdaterPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.PublishChatRoomSummaryUpdatePort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatMessageService implements ChatMessageUseCase {

    private final PublishChatMessagePort publishChatMessagePort;
    private final ChatReadCheckPointUpdaterPort chatReadCheckPointUpdaterPort;
    private final SendMessageToClientPort sendMessageToClientPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final ChatRoomSummaryUpdaterPort chatRoomSummaryUpdaterPort;
    private final PublishChatRoomSummaryUpdatePort publishChatRoomSummaryUpdatePort;
    private final ReadMessageMapper readMessageMapper;

    @Override
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {
        Boolean result = publishChatMessagePort.publishChatMessage(sendMessageRequestDto);

        if (!result) {
            throw new BaseException(BaseResponseStatus.FAILED_PUBLISH_MESSAGE);
        }
    }

    //@Transactional
    @Override
    public void updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto) {
        final Boolean updated = chatReadCheckPointUpdaterPort.updateReadCheckPoint(readMessageRequestDto);
        if (!updated) return;

        final String opponentUuid = chatRoomMemberRepositoryPort.findOpponentUuid(
                readMessageRequestDto.getChatRoomUuid(), readMessageRequestDto.getMemberUuid()
        );

        chatRoomSummaryUpdaterPort.updateOnRead(readMessageRequestDto);

        sendMessageToClientPort.sendMessage(readMessageRequestDto, opponentUuid);

        publishChatRoomSummaryUpdatePort.publishChatRoomSummaryUpdate(
                readMessageMapper.toChatRoomSummaryUpdateEventByRead(readMessageRequestDto)
        );

    }
}
