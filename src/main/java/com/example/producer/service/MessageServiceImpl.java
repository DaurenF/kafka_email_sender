package com.example.producer.service;


import com.example.producer.model.MessageDto;
import com.example.producer.model.entity.Message;
import com.example.producer.repository.MessageRepository;
import com.example.producer.service.kafka.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final KafkaMessageProducer kafkaMessageProducer;

    @Override
    public void sendMessage(MessageDto messageDto) {
        Message message = saveMessage(messageDto);
        kafkaMessageProducer.sendMessage(message);
    }

    @Override
    public List<Message> getLastTenMessages() {
        return messageRepository.findLatestTenMessages();
    }

    @Override
    public List<Message> getMessageBySender(String sender) {
        return messageRepository.findBySender(sender);
    }

    @Override
    public void setMessageTransmitted(Long id, boolean status) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            message.get().setTransmitted(status);
            messageRepository.save(message.get());
        }
    }

    private Message saveMessage(MessageDto messageDto) {
        Message message = new Message(messageDto.getSender(), messageDto.getMessage(), false);
        return messageRepository.save(message);
    }
}
