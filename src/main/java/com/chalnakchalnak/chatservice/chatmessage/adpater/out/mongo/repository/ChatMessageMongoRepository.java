package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.document.ChatMessageDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChatMessageMongoRepository extends MongoRepository<ChatMessageDocument, String> {
    List<ChatMessageDocument> findByChatRoomUuidAndSentAtGreaterThanOrderBySentAtDescMessageUuidDesc(String chatRoomUuid, LocalDateTime sentAt, Pageable pageable);

    List<ChatMessageDocument> findByChatRoomUuidAndSentAtLessThanOrderBySentAtDescMessageUuidDesc(String chatRoomUuid, LocalDateTime sentAt, Pageable pageable);

    List<ChatMessageDocument> findByChatRoomUuidAndSentAtAndMessageUuidLessThanOrderBySentAtDescMessageUuidDesc(String chatRoomUuid, LocalDateTime sentAt, String messageUuid, Pageable pageable);
    Optional<ChatMessageDocument> findByMessageUuid(String messageUuid);

}
