package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.ExitChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomMemberExitUpdaterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Repository
@RequiredArgsConstructor
public class ChatRoomMemberExitUpdater implements ChatRoomMemberExitUpdaterPort {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateExitedAt(ExitChatRoomRequestDto exitChatRoomRequestDto) {
        Query query = new Query(
                Criteria.where("chatRoomUuid").is(exitChatRoomRequestDto.getChatRoomUuid())
                        .and("memberUuid").is(exitChatRoomRequestDto.getMemberUuid())
        );

        Update update = new Update()
                .set("chatRoomUuid", exitChatRoomRequestDto.getChatRoomUuid())
                .set("memberUuid", exitChatRoomRequestDto.getMemberUuid())
                .set("exitedAt", LocalDateTime.now(ZoneId.of("Asia/Seoul")));

        mongoTemplate.upsert(query, update, "chat_room_exit");
    }
}
