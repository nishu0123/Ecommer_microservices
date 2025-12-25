package com.Ecommerce.notification_service.notification_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-random-topic")
    public void HandleUserRandomTopic_1(String producedMessageFromNotificationService)
    {
        log.info("message received in  HandleUserRandomTopic_1 {}" , producedMessageFromNotificationService);
    }

    @KafkaListener(topics = "user-random-topic")
    public void HandleUserRandomTopic_2(String producedMessageFromNotificationService)
    {
        log.info("message received in  HandleUserRandomTopic_2 {}" , producedMessageFromNotificationService);
    }


    @KafkaListener(topics = "user-random-topic")
    public void HandleUserRandomTopic_3(String producedMessageFromNotificationService)
    {
        log.info("message received in  HandleUserRandomTopic_3 {}" , producedMessageFromNotificationService);
    }

    @KafkaListener(topics = "user-random-topic")
    public void HandleUserRandomTopic_4(String producedMessageFromNotificationService)
    {
        log.info("message received in  HandleUserRandomTopic_4 {}" , producedMessageFromNotificationService);
    }

}
