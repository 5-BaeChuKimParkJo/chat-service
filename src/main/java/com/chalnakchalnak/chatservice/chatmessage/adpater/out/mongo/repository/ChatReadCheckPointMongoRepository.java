package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatReadCheckPointMongoRepository extends MongoRepository<ChatReadCheckPointDocument, String> {

    Optional<ChatReadCheckPointDocument> findByChatRoomUuidAndMemberUuid(String chatRoomUuid, String memberUuid);
}
