package com.chalnakchalnak.chatservice.chatmessage.adpater.out.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.SendMessageRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.ImageKeyValidatorPort;
import com.chalnakchalnak.chatservice.common.exception.BaseException;
import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class S3ImageKeyValidator implements ImageKeyValidatorPort {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 이미지 타입 메시지일 경우, 키 형식 및 S3 존재 여부 검증
     */
    @Override
    public void validateImageMessage(SendMessageRequestDto sendMessageRequestDto) {
        final String key = sendMessageRequestDto.getMessage();

        validateKeyFormat(key);
        validateOwnership(sendMessageRequestDto.getSenderUuid(), key);
        validateS3Existence(key);
    }

    /**
     * key 형식 검증: chat/{memberUuid}/images/{uuid}.확장자
     */
    private void validateKeyFormat(String key) {
        if (!key.matches("^chat/[\\w-]+/images/[\\w-]+\\.[a-zA-Z0-9]+$")) {
            throw new BaseException(BaseResponseStatus.INVALID_IMAGE_MESSAGE_KEY_FORMAT);
        }
    }

    /**
     * senderUuid가 key 내 포함된 memberUuid와 일치하는지 검증
     */
    private void validateOwnership(String senderUuid, String key) {
        String[] parts = key.split("/");
        if (parts.length < 3 || !parts[1].equals(senderUuid)) {
            throw new BaseException(BaseResponseStatus.INVALID_IMAGE_MESSAGE_SENDER);
        }
    }

    /**
     * 실제 S3 객체 존재 여부 확인
     */
    private void validateS3Existence(String key) {
        try {
            amazonS3.getObjectMetadata(new GetObjectMetadataRequest(bucket, key));
        } catch (AmazonS3Exception e) {
            if (e.getStatusCode() == 404) {
                throw new BaseException(BaseResponseStatus.IMAGE_FILE_NOT_FOUND_IN_S3);
            }
            throw new BaseException(BaseResponseStatus.FAILED_TO_ACCESS_S3);
        }
    }
}
