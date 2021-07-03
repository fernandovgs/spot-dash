package com.spot.dash.spotdashconsumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserConsumer {
    private static final String TOPIC = "users";
    private static final String GROUP_ID = "user-consumer";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consumer(String message) {
        log.info(message);
    }
}
