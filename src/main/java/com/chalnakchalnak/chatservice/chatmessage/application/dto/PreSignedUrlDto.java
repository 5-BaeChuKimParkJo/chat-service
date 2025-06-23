package com.chalnakchalnak.chatservice.chatmessage.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PreSignedUrlDto {

    private String key;
    private String contentType;

    @Builder
    public PreSignedUrlDto(String key, String contentType) {
        this.key = key;
        this.contentType = contentType;
    }
}