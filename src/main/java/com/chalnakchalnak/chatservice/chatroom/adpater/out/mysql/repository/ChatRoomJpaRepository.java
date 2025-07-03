package com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.repository;

import com.chalnakchalnak.chatservice.chatroom.adpater.out.mysql.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomEntity, Long> {
    Optional<ChatRoomEntity> findByChatRoomUuid(String chatRoomUuid);
    Boolean existsByChatRoomUuid(String chatRoomUuid);
    List<ChatRoomEntity> findByPostUuid(String postUuid);
}

