package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMessageRequestVo {

    private String chatRoomUuid;
    private String senderUuid;
    private String message;

}
