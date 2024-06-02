package ru.below.shopservices.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "stock-services",groupId = "shopGroup")
    public void consumeStock(String message) {
        LOGGER.info(String.format("Consumed message: %s", message));
    }
    @KafkaListener(topics = "bank-services",groupId = "shopGroup")
    public void consumeBank(String message) {
        LOGGER.info(String.format("Consumed message: %s", message));
    }
}
