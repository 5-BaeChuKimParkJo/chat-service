package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomMemberJpaRepository extends JpaRepository<ChatRoomMemberEntity, Long> {

    @Query("""
        SELECT r.id
        FROM ChatRoomEntity r
        JOIN ChatRoomMemberEntity m ON r.chatRoomUuid = m.chatRoomUuid
        WHERE r.postUuid = :postUuid
          AND m.memberUuid = :buyerUuid
          AND m.role = 'BUYER'
    """)
    Optional<Long> findPrivateRoomId(@Param("postUuid") String postUuid,
                                     @Param("buyerUuid") String buyerUuid);

    Boolean existsByChatRoomUuidAndMemberUuid(String chatRoomUuid, String memberUuid);

    List<ChatRoomMemberEntity> findByChatRoomUuid(String chatRoomUuid);

    List<ChatRoomMemberEntity> findByChatRoomUuidIn(List<String> chatRoomUuids);

}

