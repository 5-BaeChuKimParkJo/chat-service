package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomSummaryUpdaterPort;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatRoomSummaryUpdater implements ChatRoomSummaryUpdaterPort {

    private final MongoTemplate mongoTemplate;

    /**
     * 메시지 수신 시 summary update
     */
    @Override
    public void updateOnMessage(ChatMessageDto chatMessageDto, String receiverUuid) {
        // 1. 수신자 summary: unreadCount 증가
        Query receiverQuery = Query.query(Criteria.where("chatRoomUuid").is(chatMessageDto.getChatRoomUuid())
                .and("memberUuid").is(receiverUuid));

        Update receiverUpdate = new Update()
                .set("chatRoomUuid", chatMessageDto.getChatRoomUuid())
                .set("memberUuid", receiverUuid)
                .set("opponentUuid", chatMessageDto.getSenderUuid())
                .set("lastMessage", chatMessageDto.getMessage())
                .set("lastMessageSentAt", chatMessageDto.getSentAt())
                .set("messageType", chatMessageDto.getMessageType())
                .set("updatedAt", LocalDateTime.now())
                .inc("unreadCount", 1);

        mongoTemplate.upsert(receiverQuery, receiverUpdate, "chat_room_summary");

        // 발신자 summary upsert
        Query senderQuery = Query.query(Criteria.where("chatRoomUuid").is(chatMessageDto.getChatRoomUuid())
                .and("memberUuid").is(chatMessageDto.getSenderUuid()));

        Update senderUpdate = new Update()
                .set("chatRoomUuid", chatMessageDto.getChatRoomUuid())
                .set("memberUuid", chatMessageDto.getSenderUuid())
                .set("opponentUuid", receiverUuid)
                .set("lastMessage", chatMessageDto.getMessage())
                .set("lastMessageSentAt", chatMessageDto.getSentAt())
                .set("messageType", chatMessageDto.getMessageType())
                .set("updatedAt", LocalDateTime.now())
                .set("unreadCount", 0);

        mongoTemplate.upsert(senderQuery, senderUpdate, "chat_room_summary");
    }

    /**
     * 특정 메시지 읽음 처리 시 이떄까지 발송된 모든 메시지를 읽었음으로 판단
     */
    @Override
    public void updateOnRead(ReadMessageRequestDto readMessageRequestDto) {
        Query query = Query.query(Criteria.where("chatRoomUuid").is(readMessageRequestDto.getChatRoomUuid())
                .and("memberUuid").is(readMessageRequestDto.getMemberUuid()));

        Update update = new Update()
                .set("unreadCount", 0)
                .set("updatedAt", LocalDateTime.now());

        mongoTemplate.updateFirst(query, update, "chat_room_summary");
    }
}

