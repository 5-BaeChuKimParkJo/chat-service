package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.ReplyPreviewVo;
import com.chalnakchalnak.chatservice.chatmessage.domain.MessageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMessagesResponseVo {

    private String messageUuid;
    private String chatRoomUuid;
    private String senderUuid;
    private String message;
    private String messageType;
    private String sentAt;
    private String replyToMessageUuid;
    private ReplyPreviewVo replyPreview;

    @Builder
    public GetMessagesResponseVo(
            String messageUuid, String chatRoomUuid, String senderUuid,
            String message, String messageType, String sentAt,
            String replyToMessageUuid, ReplyPreviewVo replyPreview
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
