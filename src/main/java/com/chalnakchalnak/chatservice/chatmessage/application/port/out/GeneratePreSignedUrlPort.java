package com.chalnakchalnak.chatservice.chatmessage.application.port.out;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.PreSignedUrlDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.PreSignedUrlResponseDto;

public interface GeneratePreSignedUrlPort {

    PreSignedUrlResponseDto generatePreSignedUrl(PreSignedUrlDto requestDto);
}
