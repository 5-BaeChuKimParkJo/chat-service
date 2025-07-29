package com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.consumer;

import com.chalnakchalnak.chatservice.chatroom.application.dto.in.CreateChatRoomRequestDto;
import com.chalnakchalnak.chatservice.chatroom.application.port.in.ChatRoomUseCase;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaWinningBidConsumer {

    private final ChatRoomUseCase chatRoomUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "auction-service.winning-bid", containerFactory = "singleFactory")
    public void consume(String payload, Acknowledgment ack) {

        try {
            CreateChatRoomRequestDto chatRoomDto = objectMapper.readValue(payload, CreateChatRoomRequestDto.class);

            log.info("post : "+chatRoomDto.getPostUuid()+" seller : "+chatRoomDto.getSellerUuid()+" buyer : "+chatRoomDto.getBuyerUuid()+" type : "+chatRoomDto.getChatRoomType().toString());
            try {
                chatRoomUseCase.createPrivateChatRoom(chatRoomDto);
            } catch (DuplicateKeyException e) {
                log.warn("중복 채팅방, 생성 생략: {}", e.getMessage());
                ack.acknowledge();
            } catch (BaseException e) {
                log.error("채팅방 생성 실패 - 치명적인 도메인 에러: {}", e.getMessage());
            } catch (Exception e) {
                log.error("채팅방 생성 중 예외 발생", e);
            }

            ack.acknowledge();
        } catch (Exception e) {
            log.error("Kafka 메시지 처리 실패, 재시도 수행", e);
        }
    }
}
