package ru.below.stockservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.below.stockservices.kafka.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class MessageController {
    @Autowired
    private KafkaProducer kafkaProducer;
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @GetMapping("/publish")
    public ResponseEntity<String>publish(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok(message);
    }
}
