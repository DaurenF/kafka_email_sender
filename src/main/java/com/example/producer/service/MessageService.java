package com.example.producer.service;

import com.example.producer.model.MessageDto;
import com.example.producer.model.entity.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageDto messageDto);

    List<Message> getLastTenMessages();

    List<Message> getMessageBySender(String sender);

    void setMessageTransmitted(Long id, boolean status);
}
