package com.chalnakchalnak.chatservice.common.exception;

import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final BaseResponseStatus status;
    private final String memberUuid;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
        this.memberUuid = null;
    }

    public BaseException(BaseResponseStatus status, String memberUuid) {
        super(status.getMessage());
        this.status = status;
        this.memberUuid = memberUuid;
    }
}