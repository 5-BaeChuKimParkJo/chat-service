package com.chalnakchalnak.chatservice.chatmessage.application.service;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.PreSignedUrlRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.PreSignedUrlResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.mapper.PreSignedUrlMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.PreSignedUrlUseCase;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.GeneratePreSignedUrlPort;
import com.chalnakchalnak.chatservice.chatroom.application.port.out.GenerateUuidPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PreSignedUrlService implements PreSignedUrlUseCase {

    private final GeneratePreSignedUrlPort generatePreSignedUrlPort;
    private final PreSignedUrlMapper preSignedUrlMapper;
    private final GenerateUuidPort generateUuidPort;

    public PreSignedUrlResponseDto generatePreSignedUrl(PreSignedUrlRequestDto preSignedUrlRequestDto) {
        return generatePreSignedUrlPort.generatePreSignedUrl(
                preSignedUrlMapper.toPreSignedUrlDto(preSignedUrlRequestDto, generateUuidPort.generateUuid())
        );
    }

}
