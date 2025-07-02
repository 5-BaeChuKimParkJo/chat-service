package com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisMessagePublisher {

    private final StringRedisTemplate redisTemplate;

    public void publish(String roomUuid, String message) {
        redisTemplate.convertAndSend("chatroom." + roomUuid, message);
    }

    public void publishRead(String opponentUuid, String message) {
        redisTemplate.convertAndSend("chatroom.read." + opponentUuid, message);
    }
}
