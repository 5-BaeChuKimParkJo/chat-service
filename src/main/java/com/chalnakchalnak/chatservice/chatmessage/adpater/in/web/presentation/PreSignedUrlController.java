package com.chalnakchalnak.chatservice.chatmessage.adpater.in.web.presentation;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper.PreSignedUrlVoMapper;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.PreSignedUrlRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.PreSignedUrlResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.application.port.in.PreSignedUrlUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pre-signed-url")
public class PreSignedUrlController {

    private final PreSignedUrlUseCase preSignedUrlUseCase;
    private final PreSignedUrlVoMapper preSignedUrlVoMapper;

    @Operation(
            summary = "Get AWS S3 Pre-signed URL for image upload",
            description = "이미지 업로드를 위한 AWS S3 Pre-signed URL을 요청합니다. <br>지원하는 이미지 형식: png, jpeg, webp, bmp",
            tags = {"chat-message"}
    )
    @GetMapping
    public PreSignedUrlResponseVo getPreSignedUrl(
            @RequestHeader("X-Member-Uuid") String memberUuid,
            @ModelAttribute @Valid PreSignedUrlRequestVo preSignedUrlRequestVo
    ) {
        return preSignedUrlVoMapper.toPreSignedUrlResponseVo(
                preSignedUrlUseCase.generatePreSignedUrl(
                        preSignedUrlVoMapper.toPreSignedUrlRequestDto(preSignedUrlRequestVo, memberUuid)
                )
        );
    }
}
