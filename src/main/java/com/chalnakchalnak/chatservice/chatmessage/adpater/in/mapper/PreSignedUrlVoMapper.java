package com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.PreSignedUrlRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.PreSignedUrlResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.PreSignedUrlRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.PreSignedUrlResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PreSignedUrlVoMapper {

    public PreSignedUrlRequestDto toPreSignedUrlRequestDto(PreSignedUrlRequestVo preSignedUrlRequestVo, String memberUuid) {
        return PreSignedUrlRequestDto.builder()
                .memberUuid(memberUuid)
                .contentType(preSignedUrlRequestVo.getContentType())
                .build();
    }

    public PreSignedUrlResponseVo toPreSignedUrlResponseVo(PreSignedUrlResponseDto preSignedUrlResponseDto) {
        return PreSignedUrlResponseVo.builder()
                .url(preSignedUrlResponseDto.getUrl())
                .fields(preSignedUrlResponseDto.getFields())
                .build();
    }
}
