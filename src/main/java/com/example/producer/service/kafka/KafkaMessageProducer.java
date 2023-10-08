package com.example.producer.service.kafka;

import com.example.producer.model.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    private static final String TOPIC = "message.send";

    @Async
    public void sendMessage(Message message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
