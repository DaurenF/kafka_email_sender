package com.example.producer.controller;

import com.example.producer.model.MessageDto;
import com.example.producer.service.MessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @PostMapping(consumes = "application/xml")
    public void sendMessage(@Valid @RequestBody MessageDto message) {
        log.info("/message post: {}", message.toString());
        messageService.sendMessage(message);
    }

    @GetMapping(produces = "application/xml")
    public ResponseEntity<Object> getMessage(@RequestParam String sender) {
        log.info("/message get: {}", sender);
        if (sender == null || sender.isEmpty()) return ResponseEntity.ok(messageService.getLastTenMessages());
        return ResponseEntity.ok(messageService.getMessageBySender(sender));
    }


}
