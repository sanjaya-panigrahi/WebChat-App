package com.webchat.chatapp.chat_service.service;


import com.webchat.chatapp.chat_service.consumer.ChatMessageConsumer;
import com.webchat.chatapp.chat_service.entity.ChatMessage;
import com.webchat.chatapp.chat_service.repository.ChatMessageRepository;
import com.webchat.chatapp.chatroom_service.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final KafkaTemplate<String, Object> template;
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true).orElseThrow();
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }



//    public void consumeChatEventsFromTopicANDNotify(String customer){
//        log.info("ChatMessageService : consumeChatEventsFromTopicANDNotify ");
//        chatMessageConsumer.consumeChatEventsFromTopicANDNotify(customer);
//    }
}