package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PreSignedUrlRequestVo {

    @NotBlank(message = "파일 형식은 필수입니다.")
    @Pattern(
            regexp = "image/(png|jpeg|webp|bmp)",
            message = "지원하지 않는 이미지 형식입니다."
    )
    private String contentType;
}

