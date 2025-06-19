package com.chalnakchalnak.chatservice.chatmessage.application.port.in;

import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.PreSignedUrlRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.PreSignedUrlResponseDto;

public interface PreSignedUrlUseCase {

    PreSignedUrlResponseDto generatePreSignedUrl(PreSignedUrlRequestDto preSignedUrlRequestDto);
}
