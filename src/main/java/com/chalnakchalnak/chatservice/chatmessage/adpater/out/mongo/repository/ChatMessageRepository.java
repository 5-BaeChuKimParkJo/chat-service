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
import com.mongodb.MongoBulkWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatMessageRepository implements ChatMessageRepositoryPort {

    private final ChatMessageMongoRepository chatMessageMongoRepository;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;
    private final ChatRoomSummaryUpdaterPort chatRoomSummaryUpdaterPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final PublishChatRoomSummaryUpdatePort publishChatRoomSummaryUpdatePort;
    private final ChatMessageBulkOps chatMessageBulkOps;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public void bulkUpsertMessages(List<ChatMessageDto> messageDtoList) {
        try {
            List<String> chatRoomUuids = messageDtoList.stream()
                    .map(ChatMessageDto::getChatRoomUuid)
                    .distinct()
                    .collect(Collectors.toList());

            Map<String, Map<String, String>> opponentMap = chatRoomMemberRepositoryPort.findAllOpponentUuids(chatRoomUuids);

            List<String> receiverUuids = new ArrayList<>();
            for (ChatMessageDto dto : messageDtoList) {
                String receiverUuid = opponentMap
                        .get(dto.getChatRoomUuid())
                        .get(dto.getSenderUuid());

                if (receiverUuid == null) {
                    throw new BaseException(BaseResponseStatus.CHAT_ROOM_MEMBER_NOT_FOUND);
                }

                receiverUuids.add(receiverUuid);
            }

            chatRoomSummaryUpdaterPort.bulkUpdateOnMessages(messageDtoList, receiverUuids);

            for(int i = 0; i < messageDtoList.size(); i++) {
                publishChatRoomSummaryUpdatePort.publishChatRoomSummaryUpdate(
                        chatMessageMapper.toChatRoomSummaryUpdateEventByMessage(messageDtoList.get(i), receiverUuids.get(i))
                );
            }
        } catch (Exception e) {
            log.error("MongoDB Bulk Upsert 실패: {}", e.getMessage());
            throw new BaseException(BaseResponseStatus.FAILED_MESSAGE_PROCESSING);
        }
    }

    @Override
    public void bulkSaveMessages(List<ChatMessageDto> messages) {
        try {
            chatMessageBulkOps.saveMessages(messages);

        } catch (MongoBulkWriteException e) {
            log.error("MongoDB Bulk Insert 실패: {}", e.getMessage(), e);
            throw new BaseException(BaseResponseStatus.FAILED_MESSAGE_PROCESSING);
        } catch (Exception e) {
            log.error("MongoDB 저장 중 예외 발생", e);
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