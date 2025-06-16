package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatReadCheckPointDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatReadCheckPointRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ChatReadCheckPointRepository implements ChatReadCheckPointRepositoryPort {

    private final ChatReadCheckPointMongoRepository chatReadCheckPointMongoRepository;
    private final ChatReadCheckPointDocumentMapper chatReadCheckPointDocumentMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto) {
        final LocalDateTime sentAt = readMessageRequestDto.getLastReadMessageSentAt();
        final LocalDateTime now = LocalDateTime.now();

        chatReadCheckPointMongoRepository.findByChatRoomUuidAndMemberUuid(
                readMessageRequestDto.getChatRoomUuid(), readMessageRequestDto.getMemberUuid())
                .ifPresentOrElse(
                        document -> {
                            document.updateCheckpoint(sentAt, now);
                            chatReadCheckPointMongoRepository.save(document);
                        },
                        () -> {
                            ChatReadCheckPointDocument newDocument =
                                    chatReadCheckPointDocumentMapper.toBaseDocument(
                                            readMessageRequestDto, sentAt, now
                                    );
                            chatReadCheckPointMongoRepository.save(newDocument);
                        });
    }
}

