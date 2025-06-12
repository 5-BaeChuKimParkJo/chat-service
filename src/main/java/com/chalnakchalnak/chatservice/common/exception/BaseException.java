package com.chalnakchalnak.chatservice.common.exception;

import com.chalnakchalnak.chatservice.common.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final BaseResponseStatus status;
    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}