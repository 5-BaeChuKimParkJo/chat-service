package com.chalnakchalnak.chatservice.chatroom.adpater.out.mongo.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_room_exit")
@CompoundIndexes({
        @CompoundIndex(
                name = "idx_chatroom_exit",
                def = "{'chatRoomUuid': 1, 'memberUuid': 1}",
                unique = true
        )
})
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
