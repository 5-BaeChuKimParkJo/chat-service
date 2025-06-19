package com.chalnakchalnak.chatservice.common.util.uuid;

import com.chalnakchalnak.chatservice.chatroom.application.port.out.GenerateUuidPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JavaUuidAdapter implements GenerateUuidPort {

    @Override
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
