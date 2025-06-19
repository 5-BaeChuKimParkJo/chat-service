package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.entity.ChatRoomSummaryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomSummaryMongoRepository extends MongoRepository<ChatRoomSummaryDocument, String> {

    Optional<List<ChatRoomSummaryDocument>> findAllByMemberUuidOrderByLastMessageSentAtDesc (String memberUuid);
    void deleteByChatRoomUuidAndMemberUuid (String chatRoomUuid, String memberUuid);
}
