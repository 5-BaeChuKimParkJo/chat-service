package com.chalnakchalnak.chatservice.chatmessage.application.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class PreSignedUrlResponseDto {

    private String url;
    private Map<String, String> fields;

    @Builder
    public PreSignedUrlResponseDto(String url, Map<String, String> fields) {
        this.url = url;
        this.fields = fields;
    }
}
