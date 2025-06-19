package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.entity.ChatReadCheckPointDocument;
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
    public Boolean updateReadCheckPoint(ReadMessageRequestDto readMessageRequestDto) {
        final LocalDateTime sentAt = readMessageRequestDto.getLastReadMessageSentAt();
        final LocalDateTime now = LocalDateTime.now();

        Query query = Query.query(Criteria.where("chatRoomUuid").is(readMessageRequestDto.getChatRoomUuid())
                .and("memberUuid").is(readMessageRequestDto.getMemberUuid()));

        var existing = mongoTemplate.findOne(query, ChatReadCheckPointDocument.class, "chat_read_checkpoint");

        // 2. 없으면 insert
        if (existing == null) {
            ChatReadCheckPointDocument doc = ChatReadCheckPointDocument.builder()
                    .chatRoomUuid(readMessageRequestDto.getChatRoomUuid())
                    .memberUuid(readMessageRequestDto.getMemberUuid())
                    .lastReadMessageSentAt(sentAt)
                    .updatedAt(now)
                    .build();
            mongoTemplate.insert(doc, "chat_read_checkpoint");
            return true;
        }

        // 3. sentAt이 이전 값보다 클 때만 update
        if (sentAt.isAfter(existing.getLastReadMessageSentAt())) {
            Update update = new Update()
                    .set("lastReadMessageSentAt", sentAt)
                    .set("updatedAt", now);
            mongoTemplate.updateFirst(query, update, "chat_read_checkpoint");
            return true;
        }

        // 변경할 필요 없음
        return false;
    }
}