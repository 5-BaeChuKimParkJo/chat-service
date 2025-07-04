package com.chalnakchalnak.chatservice.chatroom.adpater.out.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaWinningBidConsumerConfig {

    private final ConsumerFactory<String, String> consumerFactory;

    /**
     * 단건 처리용 컨테이너 팩토리
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> singleFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(false);
        return factory;
    }

}
