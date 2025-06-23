package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_room_exit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatRoomMemberExitDocument {

    @Id
    private String id;

    private String chatRoomUuid;
    private String memberUuid;
    private LocalDateTime exitedAt;

    @Builder
    public ChatRoomMemberExitDocument(String chatRoomUuid, String memberUuid, LocalDateTime exitedAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.exitedAt = exitedAt;
    }
}
