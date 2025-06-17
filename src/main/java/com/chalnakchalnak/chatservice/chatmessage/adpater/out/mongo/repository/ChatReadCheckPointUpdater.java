package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatReadCheckPointUpdaterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ChatReadCheckPointUpdater implements ChatReadCheckPointUpdaterPort {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto) {
        final LocalDateTime sentAt = readMessageRequestDto.getLastReadMessageSentAt();
        final LocalDateTime now = LocalDateTime.now();

        Query query = Query.query(Criteria.where("chatRoomUuid").is(readMessageRequestDto.getChatRoomUuid())
                .and("memberUuid").is(readMessageRequestDto.getMemberUuid()));

        Update update = new Update()
                .set("lastReadMessageSentAt", sentAt)
                .set("updatedAt", now)
                .setOnInsert("chatRoomUuid", readMessageRequestDto.getChatRoomUuid())
                .setOnInsert("memberUuid", readMessageRequestDto.getMemberUuid());

        mongoTemplate.upsert(query, update, "chat_read_checkpoint");

    }
}

