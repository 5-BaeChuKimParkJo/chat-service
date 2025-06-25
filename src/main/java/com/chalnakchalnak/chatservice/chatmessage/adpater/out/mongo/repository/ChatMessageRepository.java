package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatMessageDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.mapper.ChatMessageMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberRepositoryPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomSummaryUpdaterPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.PublishChatRoomSummaryUpdatePort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository implements ChatMessageRepositoryPort {

    private final ChatMessageMongoRepository chatMessageMongoRepository;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;
    private final ChatRoomSummaryUpdaterPort chatRoomSummaryUpdaterPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final PublishChatRoomSummaryUpdatePort publishChatRoomSummaryUpdatePort;
    private final ChatMessageMapper chatMessageMapper;

//    @Transactional
    @Override
    public void processMessage(ChatMessageDto chatMessageDto) {
        try {
            chatMessageMongoRepository.save(
                    chatMessageDocumentMapper.toChatMessageDocument(chatMessageDto)
            );

            final String receiverUuid = chatRoomMemberRepositoryPort.findOpponentUuid(
                    chatMessageDto.getChatRoomUuid(), chatMessageDto.getSenderUuid()
            );

            chatRoomSummaryUpdaterPort.updateOnMessage(chatMessageDto, receiverUuid);

            publishChatRoomSummaryUpdatePort.publishChatRoomSummaryUpdate(
                    chatMessageMapper.toChatRoomSummaryUpdateEventByMessage(chatMessageDto, receiverUuid)
            );
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAILED_MESSAGE_PROCESSING);
        }
    }

    @Override
    public GetMessagesResponseDto findByMessageUuid(String messageUuid) {
        return chatMessageMongoRepository
                .findByMessageUuid(messageUuid)
                .map(chatMessageDocumentMapper::toGetMessagesResponseDto)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CHAT_MESSAGE_NOT_FOUND));
    }
}