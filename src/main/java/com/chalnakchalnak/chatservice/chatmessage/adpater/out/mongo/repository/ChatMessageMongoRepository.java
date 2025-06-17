package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatMessageDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageMongoRepository extends MongoRepository<ChatMessageDocument, String> {
    List<ChatMessageDocument> findTopByChatRoomUuidOrderByIdDesc(String chatRoomUuid, Pageable pageable);
    List<ChatMessageDocument> findByChatRoomUuidAndIdLessThanOrderByIdDesc(String chatRoomUuid, ObjectId lastId, Pageable pageable);

}
