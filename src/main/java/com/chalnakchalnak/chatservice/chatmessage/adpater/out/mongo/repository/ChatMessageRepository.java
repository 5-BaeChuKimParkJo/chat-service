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
import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatMessageRepository implements ChatMessageRepositoryPort {

    private final MongoTemplate mongoTemplate;
    private final ChatMessageMongoRepository chatMessageMongoRepository;
    private final ChatMessageDocumentMapper chatMessageDocumentMapper;
    private final ChatRoomSummaryUpdaterPort chatRoomSummaryUpdaterPort;
    private final ChatRoomMemberRepositoryPort chatRoomMemberRepositoryPort;
    private final PublishChatRoomSummaryUpdatePort publishChatRoomSummaryUpdatePort;
    private final ChatMessageMapper chatMessageMapper;

    private static final int BATCH_SIZE = 1000;

//    @Transactional
    @Override
    public void processMessage(ChatMessageDto chatMessageDto) {
        try {
//            chatMessageMongoRepository.save(
//                    chatMessageDocumentMapper.toChatMessageDocument(chatMessageDto)
//            );

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

//    @Transactional
    @Override
    public void bulkSaveMessages(List<ChatMessageDto> messages) {
        try {
            List<ChatMessageDto> batch = new ArrayList<>();

            for (int i = 0; i < messages.size(); i++) {
                batch.add(messages.get(i));

                if (batch.size() == BATCH_SIZE || i == messages.size() - 1) {
                    BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, "chat_message");

                    batch.forEach(message ->
                            bulkOps.insert(chatMessageDocumentMapper.toChatMessageDocument(message))
                    );

                    BulkWriteResult result = bulkOps.execute();
                    log.info("MongoDB Bulk Insert 성공: {}건", result.getInsertedCount());

                    batch.clear();
                }
            }

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