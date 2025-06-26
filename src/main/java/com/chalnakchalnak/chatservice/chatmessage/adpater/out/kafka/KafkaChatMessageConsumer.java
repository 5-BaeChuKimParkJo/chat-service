package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket.exception.WebSocketErrorMessage;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatMessageConsumer {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepositoryPort chatMessageRepositoryPort;
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;

    String endKey = "chat:perf:end-time";
    String consumeCountKey = "chat:perf:total-consume-count";


    @KafkaListener(topics = "chat.private.room")
    public void consume(List<String> payloads, Acknowledgment ack) {
        String messageSenderUuid = null;
        log.info("Batch size: {}", payloads.size());
        try {
            List<ChatMessageDto> messageList = new ArrayList<>();
//            Map<String, ChatMessageDto> lastMessageByRoom = new HashMap<>();
            for (String payload : payloads) {
                ChatMessageDto message = objectMapper.readValue(payload, ChatMessageDto.class);
                messageList.add(message);

                messageSenderUuid = message.getSenderUuid();
                chatMessageRepositoryPort.processMessage(message);

                messagingTemplate.convertAndSend("/topic/chatroom/" + message.getChatRoomUuid(), message);
                log.info("Kafka 메시지 처리 성공: {}", message.getMessage());
            }
            chatMessageRepositoryPort.bulkSaveMessages(messageList);

            redisTemplate.opsForValue().set(endKey, String.valueOf(System.currentTimeMillis()));
            redisTemplate.opsForValue().increment(consumeCountKey);

            ack.acknowledge();
        } catch (DuplicateKeyException e) {
            ack.acknowledge();
            log.warn("중복된 메시지 수신, 전송 생략: {}", e.getMessage());
        } catch (BaseException e) {
            messagingTemplate.convertAndSendToUser(
                    messageSenderUuid,
                    "/queue/errors",
                    new WebSocketErrorMessage(BaseResponseStatus.FAILED_MESSAGE_PROCESSING)
            );
        }
        catch (Exception e) {
            log.error("Kafka 메시지 소비 실패, 재시도 수행", e);
            messagingTemplate.convertAndSendToUser(
                    messageSenderUuid,
                    "/queue/errors",
                    new WebSocketErrorMessage(BaseResponseStatus.FAILED_CONSUME_MESSAGE)
            );
        }
    }
//@KafkaListener(topics = "chat.private.room")
//public void consume(List<String> payloads, Acknowledgment ack) {
//    String messageSenderUuid = null;
//    log.info("Batch size: {}", payloads.size());
//    try {
//        List<ChatMessageDto> messageList = new ArrayList<>();
//
//        for (String payload : payloads) {
//            ChatMessageDto message = objectMapper.readValue(payload, ChatMessageDto.class);
//            messageList.add(message);
//
//            messageSenderUuid = message.getSenderUuid();
//            messagingTemplate.convertAndSend("/topic/chatroom/" + message.getChatRoomUuid(), message);
//            log.info("Kafka 메시지 처리 성공: {}", message.getMessage());
//        }
//
//        chatMessageRepositoryPort.bulkSaveMessages(messageList);
//        chatMessageRepositoryPort.bulkUpdateSummaries(messageList);
//
//        redisTemplate.opsForValue().set(endKey, String.valueOf(System.currentTimeMillis()));
//        redisTemplate.opsForValue().increment(consumeCountKey);
//
//        ack.acknowledge();
//    } catch (DuplicateKeyException e) {
//        ack.acknowledge();
//        log.warn("중복된 메시지 수신, 전송 생략: {}", e.getMessage());
//    } catch (BaseException e) {
//        log.error("Kafka 메시지 소비 실패, 재시도 수행", e);
//        messagingTemplate.convertAndSendToUser(
//                messageSenderUuid,
//                "/queue/errors",
//                new WebSocketErrorMessage(BaseResponseStatus.FAILED_MESSAGE_PROCESSING)
//        );
//    } catch (Exception e) {
//        log.error("Kafka 메시지 소비 실패, 재시도 수행", e);
//        messagingTemplate.convertAndSendToUser(
//                messageSenderUuid,
//                "/queue/errors",
//                new WebSocketErrorMessage(BaseResponseStatus.FAILED_CONSUME_MESSAGE)
//        );
//    }
//}

}