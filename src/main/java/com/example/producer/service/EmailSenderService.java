package com.example.producer.service;

import com.example.producer.model.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final MessageService messageService;
    @Value("${spring.mail.address}")
    private String emailFrom;

    @Async
    public void sendEmail(Message message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailFrom);
        mailMessage.setSubject("Message from: " + message.getSender());
        mailMessage.setText("Message content: " + message.getMessage());

        try {
            javaMailSender.send(mailMessage);
            messageService.setMessageTransmitted(message.getId(), true);
            log.info("Email sent successfully.");
        } catch (MailException e) {
            log.warn("Error sending email: " + e.getMessage());
        }
    }
}
