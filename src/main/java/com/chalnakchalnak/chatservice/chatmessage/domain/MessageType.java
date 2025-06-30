package com.chalnakchalnak.chatservice.chatmessage.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {

    TEXT("TEXT", "텍스트 메시지"),
    IMAGE("IMAGE", "이미지"),
    REPLY("REPLY", "답장 메시지"),
    SYSTEM("SYSTEM", "시스템 메시지");

    private final String code;
    private final String label;

    @JsonCreator
    public static MessageType from(String value) {
        for (MessageType type : values()) {
            if (type.code.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown message type: " + value);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
