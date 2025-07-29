package com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.document.ChatMessageDocument;
import com.chalnakchalnak.chatservice.chatmessage.adpater.out.mongo.document.ReplyPreview;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ChatMessageDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.ReplyPreviewDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.domain.enums.MessageType;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageDocumentMapper {

    public ChatMessageDocument toChatMessageDocument(ChatMessageDto chatMessageDto) {
        return ChatMessageDocument.builder()
                .messageUuid(chatMessageDto.getMessageUuid())
                .chatRoomUuid(chatMessageDto.getChatRoomUuid())
                .senderUuid(chatMessageDto.getSenderUuid())
                .message(chatMessageDto.getMessage())
                .messageType(chatMessageDto.getMessageType())
                .sentAt(chatMessageDto.getSentAt()).replyToMessageUuid(chatMessageDto.getReplyToMessageUuid())
                .replyPreview(chatMessageDto.getReplyPreview() == null ? null : ReplyPreview.builder()
                        .senderUuid(chatMessageDto.getReplyPreview().getSenderUuid())
                        .message(chatMessageDto.getReplyPreview().getMessage())
                        .messageType(MessageType.valueOf(chatMessageDto.getReplyPreview().getMessageType()))
                        .build())
                .build();
    }

    public GetMessagesResponseDto toGetMessagesResponseDto(ChatMessageDocument chatMessageDocument) {
        return GetMessagesResponseDto.builder()
                .messageUuid(chatMessageDocument.getMessageUuid())
                .chatRoomUuid(chatMessageDocument.getChatRoomUuid())
                .senderUuid(chatMessageDocument.getSenderUuid())
                .message(chatMessageDocument.getMessage())
                .messageType(chatMessageDocument.getMessageType().toString())
                .sentAt(chatMessageDocument.getSentAt())
                .replyToMessageUuid(chatMessageDocument.getReplyToMessageUuid())
                .replyPreview(
                        chatMessageDocument.getReplyPreview() != null ?
                                ReplyPreviewDto.builder()
                                        .senderUuid(chatMessageDocument.getReplyPreview().getSenderUuid())
                                        .message(chatMessageDocument.getReplyPreview().getMessage())
                                        .messageType(chatMessageDocument.getReplyPreview().getMessageType().toString())
                                        .build() : null
                )
                .build();
    }
}
