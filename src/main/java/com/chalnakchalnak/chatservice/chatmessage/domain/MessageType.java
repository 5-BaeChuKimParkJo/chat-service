package com.chalnakchalnak.chatservice.chatmessage.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    TEXT("텍스트 메시지"),
    IMAGE("이미지");

    @JsonValue
    private final String label;
}
