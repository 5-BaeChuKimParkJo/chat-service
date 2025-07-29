package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.document.ChatRoomSummaryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomSummaryMongoRepository extends MongoRepository<ChatRoomSummaryDocument, String> {

    List<ChatRoomSummaryDocument> findAllByMemberUuidOrderByLastMessageSentAtDesc (String memberUuid);
    void deleteByChatRoomUuidAndMemberUuid (String chatRoomUuid, String memberUuid);
    List<ChatRoomSummaryDocument> findAllByMemberUuid (String memberUuid);
}
