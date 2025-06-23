package com.chalnakchalnak.chatservice.chatmessage.application.dto.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.ReplyPreviewDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMessagesResponseDto {

    private String messageUuid;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String messageType;
    private LocalDateTime sentAt;
    private String replyToMessageUuid;
    private ReplyPreviewDto replyPreview;

    @Builder
    public GetMessagesResponseDto(
            String messageUuid, String chatRoomUuid, String senderUuid,
            String message, String messageType, LocalDateTime sentAt,
            String replyToMessageUuid, ReplyPreviewDto replyPreview
    ) {
        this.messageUuid = messageUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.message = message;
        this.messageType = messageType;
        this.sentAt = sentAt;
        this.replyToMessageUuid = replyToMessageUuid;
        this.replyPreview = replyPreview;
    }
}
