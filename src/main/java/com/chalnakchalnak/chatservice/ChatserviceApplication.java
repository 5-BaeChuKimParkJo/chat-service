package com.chalnakchalnak.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChatserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatserviceApplication.class, args);
    }

}
