package com.example.producer.service.kafka;

import com.example.producer.model.entity.Message;
import com.example.producer.service.EmailSenderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageConsumer {

    private final EmailSenderService emailSenderService;

    @KafkaListener(topics = "message.send", groupId = "group_id")
    public void consumeMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message messageObject = objectMapper.readValue(message, Message.class);
            emailSenderService.sendEmail(messageObject);
        } catch (IOException e) {
            log.warn("Error parsing message: " + e.getMessage());
        }
    }


}
