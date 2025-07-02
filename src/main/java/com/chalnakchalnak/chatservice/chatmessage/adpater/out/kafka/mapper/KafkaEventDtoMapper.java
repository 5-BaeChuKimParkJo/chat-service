package com.chalnakchalnak.chatservice.chatmessage.adpater.out.kafka.mapper;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ReplyPreviewDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ChatMessageRepositoryPort;
import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventDtoMapper {

    private final ChatMessageRepositoryPort chatMessageRepositoryPort;

    public ChatMessageDto toChatMessageDto(SendMessageRequestDto sendMessageRequestDto, String uuid) {
        if (sendMessageRequestDto.getMessageType() == MessageType.SYSTEM) {
            return ChatMessageDto.builder()
                    .messageUuid(uuid)
                    .chatRoomUuid(sendMessageRequestDto.getChatRoomUuid())
                    .senderUuid(sendMessageRequestDto.getSenderUuid())
                    .message(sendMessageRequestDto.getMessage())
                    .messageType(sendMessageRequestDto.getMessageType().toString())
                    .sentAt(sendMessageRequestDto.getSentAt())
                    .build();
        }

        String replyToMessageUuid = null;
        ReplyPreviewDto replyPreview = null;

        if (sendMessageRequestDto.getMessageType() == MessageType.REPLY && sendMessageRequestDto.getReplyToMessageUuid() != null) {
            GetMessagesResponseDto origin =
                    chatMessageRepositoryPort.findByMessageUuid(sendMessageRequestDto.getReplyToMessageUuid());

            replyToMessageUuid = sendMessageRequestDto.getReplyToMessageUuid();
            replyPreview = ReplyPreviewDto.builder()
                    .senderUuid(origin.getSenderUuid())
                    .message(origin.getMessage())
                    .messageType(origin.getMessageType().toString())
                    .build();
        }

        return ChatMessageDto.builder()
                .messageUuid(uuid)
                .chatRoomUuid(sendMessageRequestDto.getChatRoomUuid())
                .senderUuid(sendMessageRequestDto.getSenderUuid())
                .message(sendMessageRequestDto.getMessage())
                .messageType(sendMessageRequestDto.getMessageType().toString())
                .sentAt(sendMessageRequestDto.getSentAt())
                .replyToMessageUuid(replyToMessageUuid)
                .replyPreview(replyPreview)
                .build();
    }
}
