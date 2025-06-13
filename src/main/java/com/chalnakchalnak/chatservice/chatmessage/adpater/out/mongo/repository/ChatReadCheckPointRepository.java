package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper.ChatReadCheckPointDocumentMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.UpdateReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatReadCheckPointRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
    public void updateReadCheckPoint(UpdateReadCheckPointRequestDto updateReadCheckPointRequestDto) {
        if (!ObjectId.isValid(updateReadCheckPointRequestDto.getLastReadMessageId())) return;

        final ObjectId objectId = new ObjectId(updateReadCheckPointRequestDto.getLastReadMessageId());
        final LocalDateTime now = LocalDateTime.now();

        chatReadCheckPointMongoRepository.findByChatRoomUuidAndMemberUuid(
                updateReadCheckPointRequestDto.getChatRoomUuid(), updateReadCheckPointRequestDto.getMemberUuid())
                .ifPresentOrElse(
                        document -> {
                            document.updateCheckpoint(objectId, now);
                            chatReadCheckPointMongoRepository.save(document);
                        },
                        () -> {
                            ChatReadCheckPointDocument newDocument =
                                    chatReadCheckPointDocumentMapper.toBaseDocument(
                                            updateReadCheckPointRequestDto, objectId, now
                                    );
                            chatReadCheckPointMongoRepository.save(newDocument);
                        });

        messagingTemplate.convertAndSend(
                "/sub/chatroom/" + updateReadCheckPointRequestDto.getChatRoomUuid() + "/read",
                new UpdateReadCheckPointRequestDto(updateReadCheckPointRequestDto.getChatRoomUuid(), updateReadCheckPointRequestDto.getMemberUuid(), updateReadCheckPointRequestDto.getLastReadMessageId())
        );
    }
}

