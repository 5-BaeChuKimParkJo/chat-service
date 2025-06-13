package com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.entity;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.persistence.mysql.common.BaseEntity;
import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomMemberRole;
import com.chalnakchalnak.chatservice.chatroom.domain.enums.ChatRoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_room_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatRoomMemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "chat_room_uuid", nullable = false, length = 50)
    private String chatRoomUuid;

    @Column(name = "member_uuid", nullable = false, length = 50)
    private String memberUuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private ChatRoomMemberRole role;

    @Builder
    public ChatRoomMemberEntity(Long id, String chatRoomUuid, String memberUuid, ChatRoomMemberRole role) {
        this.id = id;
        this.chatRoomUuid = chatRoomUuid;
        this.memberUuid = memberUuid;
        this.role = role;
    }
}
