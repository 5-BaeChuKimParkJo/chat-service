package com.chalnakchalnak.chatservice.chatmessage.adpater.out.websocket.message;


import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.ReadMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.SendMessageToClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMessageToClient implements SendMessageToClientPort {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(ReadMessageRequestDto readMessageRequestDto, String opponentUuid) {
        messagingTemplate.convertAndSendToUser(
                opponentUuid,
                "/queue/chatroom/read",
                readMessageRequestDto
        );
    }


}
