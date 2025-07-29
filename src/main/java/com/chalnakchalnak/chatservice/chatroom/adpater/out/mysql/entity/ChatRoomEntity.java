package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.common.BaseEntity;
import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "chat_room",
        indexes = {
                @Index(name = "idx_chat_room_uuid", columnList = "chat_room_uuid"),
                @Index(name = "idx_post_uuid", columnList = "post_uuid")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatRoomEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "chat_room_uuid", nullable = false, unique = true, length = 50)
    private String chatRoomUuid;

    @Column(name = "post_uuid", nullable = false, length = 50)
    private String postUuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "chat_room_type", nullable = false, length = 20)
    private ChatRoomType chatRoomType;

    @Builder
    public ChatRoomEntity(Long id, String chatRoomUuid, String postUuid, ChatRoomType chatRoomType) {
        this.id = id;
        this.chatRoomUuid = chatRoomUuid;
        this.postUuid = postUuid;
        this.chatRoomType = chatRoomType;
    }
}
