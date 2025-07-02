package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.repository;

import com.chalnakchalnak.chatservice.chatroom.application.port.out.ChatRoomSummaryUpdaterPort;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomSummaryUpdater implements ChatRoomSummaryUpdaterPort {

    private final MongoTemplate mongoTemplate;

    /**
     * 메시지 수신 시 summary update
     */
    @Override
    public void bulkUpdateOnMessages(List<ChatMessageDto> chatMessageDtoList, List<String> receiverUuids) {
        BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, "chat_room_summary");

        for (int i = 0; i < chatMessageDtoList.size(); i++) {
            ChatMessageDto chatMessageDto = chatMessageDtoList.get(i);
            String receiverUuid = receiverUuids.get(i);

            // 수신자 summary
            Query receiverQuery = Query.query(Criteria.where("chatRoomUuid").is(chatMessageDto.getChatRoomUuid())
                    .and("memberUuid").is(receiverUuid));

            Update receiverUpdate = new Update()
                    .set("chatRoomUuid", chatMessageDto.getChatRoomUuid())
                    .set("memberUuid", receiverUuid)
                    .set("opponentUuid", chatMessageDto.getSenderUuid())
                    .set("lastMessage", chatMessageDto.getMessage())
                    .set("lastMessageSentAt", chatMessageDto.getSentAt())
                    .set("messageType", chatMessageDto.getMessageType())
                    .set("updatedAt", LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                    .inc("unreadCount", 1);

            bulkOps.upsert(receiverQuery, receiverUpdate);

            // 발신자 summary
            Query senderQuery = Query.query(Criteria.where("chatRoomUuid").is(chatMessageDto.getChatRoomUuid())
                    .and("memberUuid").is(chatMessageDto.getSenderUuid()));

            Update senderUpdate = new Update()
                    .set("chatRoomUuid", chatMessageDto.getChatRoomUuid())
                    .set("memberUuid", chatMessageDto.getSenderUuid())
                    .set("opponentUuid", receiverUuid)
                    .set("lastMessage", chatMessageDto.getMessage())
                    .set("lastMessageSentAt", chatMessageDto.getSentAt())
                    .set("messageType", chatMessageDto.getMessageType())
                    .set("updatedAt", LocalDateTime.now(ZoneId.of("Asia/Seoul")));

            if (chatMessageDto.getMessageType().equals("SYSTEM")) {
                senderUpdate.inc("unreadCount", 1);
            } else {
                senderUpdate.set("unreadCount", 0);
            }

            bulkOps.upsert(senderQuery, senderUpdate);
        }

        bulkOps.execute();
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
                .set("updatedAt", LocalDateTime.now(ZoneId.of("Asia/Seoul")));

        mongoTemplate.updateFirst(query, update, "chat_room_summary");
    }
}

