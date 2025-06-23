package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final ChatHandshakeInterceptor chatHandshakeInterceptor;
    private final HandshakeHandler handshakeHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry
                .addEndpoint("/ws/chat")
                .addInterceptors(chatHandshakeInterceptor)
                .setHandshakeHandler(handshakeHandler)
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        messageBrokerRegistry.enableSimpleBroker("/topic", "/queue")
                .setHeartbeatValue(new long[]{10000L, 10000L})
                        .setTaskScheduler(messageBrokerTaskScheduler());
        messageBrokerRegistry.setApplicationDestinationPrefixes("/pub");
        messageBrokerRegistry.setUserDestinationPrefix("/user");
    }

    @Bean(name = "customMessageBrokerTaskScheduler")
    public ThreadPoolTaskScheduler messageBrokerTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("wss-heartbeat-");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

}
