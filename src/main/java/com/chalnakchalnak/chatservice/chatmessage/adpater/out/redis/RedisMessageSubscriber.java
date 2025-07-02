package com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String payload = new String(message.getBody(), StandardCharsets.UTF_8);
        String topic = new String(message.getChannel(), StandardCharsets.UTF_8);

        if (topic.startsWith("chatroom.read.")) {
            String opponentUuid = topic.replace("chatroom.read.", "");
            messagingTemplate.convertAndSendToUser(opponentUuid, "/queue/chatroom/read", payload);
        } else if (topic.startsWith("chatroom.")) {
            String roomUuid = topic.replace("chatroom.", "");
            messagingTemplate.convertAndSend("/topic/chatroom/" + roomUuid, payload);
        }
    }
}