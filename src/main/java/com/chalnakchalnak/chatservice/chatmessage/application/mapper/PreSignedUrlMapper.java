package com.chalnakchalnak.chatservice.chatmessage.application.mapper;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.PreSignedUrlDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.PreSignedUrlRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.PreSignedUrlResponseDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PreSignedUrlMapper {

    public PreSignedUrlDto toPreSignedUrlDto(PreSignedUrlRequestDto preSignedUrlRequestDto, String randomUuid) {

        final String ext = preSignedUrlRequestDto.getContentType()
                .substring(preSignedUrlRequestDto.getContentType().indexOf("/") + 1);
        final String key = "chat/" + preSignedUrlRequestDto.getMemberUuid() + "/images/" + randomUuid + "." + ext;

        return PreSignedUrlDto.builder()
                .key(key)
                .contentType(preSignedUrlRequestDto.getContentType())
                .build();
    }

    public PreSignedUrlResponseDto toPreSignedUrlResponseDto(String url, Map<String, String> fields) {
        return PreSignedUrlResponseDto.builder()
                .url(url)
                .fields(fields)
                .build();
    }
}
