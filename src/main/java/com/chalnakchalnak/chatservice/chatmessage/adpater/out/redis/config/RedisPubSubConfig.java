package com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis.config;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.redis.sub.RedisMessageSubscriber;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@ConditionalOnProperty(name = "pubsub.enabled", havingValue = "true", matchIfMissing = true)
public class RedisPubSubConfig {

    @Bean
    public RedisMessageListenerContainer redisContainer(
            RedisConnectionFactory connectionFactory, RedisMessageSubscriber subscriber
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscriber, new PatternTopic("chatroom.*"));
        container.addMessageListener(subscriber, new PatternTopic("chatroom.read.*"));
        container.addMessageListener(subscriber, new PatternTopic("error.*"));
        return container;
    }
}
