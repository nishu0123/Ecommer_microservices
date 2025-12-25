package com.Ecommerce.user_service.user_service.Config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

//    @Bean
//    public KafkaAdmin kafkaAdmin(KafkaProperties properties) {
//        return new KafkaAdmin(properties.buildAdminProperties());
//    }

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_USER_RANDOM_TOPIC; //the above value will stored in this variable and i can use this variable any-where
    @Bean
    public NewTopic userRandomTopic()
    {
        return new NewTopic(KAFKA_USER_RANDOM_TOPIC , 3 , (short)1);
    }
}
