package com.chalnakchalnak.chatservice.chatmessage.application.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PreSignedUrlRequestDto {

    private String memberUuid;
    private String contentType;

    @Builder
    public PreSignedUrlRequestDto(String memberUuid, String contentType) {
        this.memberUuid = memberUuid;
        this.contentType = contentType;
    }
}
