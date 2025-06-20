package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.document.ChatRoomMemberExitDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomMemberExitMongoRepository extends MongoRepository<ChatRoomMemberExitDocument, String>  {

    Optional<ChatRoomMemberExitDocument> findByChatRoomUuidAndMemberUuid(String chatRoomUuid, String memberUuid);
}
