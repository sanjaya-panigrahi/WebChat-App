package com.webchat.chatapp.chat_service.controller;


import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import com.webchat.chatapp.chat_service.entity.ChatMessage;
import com.webchat.chatapp.chat_service.entity.ChatNotification;
import com.webchat.chatapp.chat_service.producer.ChatMessageProducer;
import com.webchat.chatapp.chat_service.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageProducer chatMessageProducer;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        log.info("Chat Message to Persist and Topic {}", chatMessage);
        // Save the message to DB
        chatMessageService.save(chatMessage);

        ChatMessageDTO chatMessageDTO = new ChatMessageDTO(
                chatMessage.getId(),
                chatMessage.getChatId(),
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                chatMessage.getContent()
        );

        // Produce the message to Kafka
        chatMessageProducer.sendChatEventsToTopic(chatMessageDTO);


    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}