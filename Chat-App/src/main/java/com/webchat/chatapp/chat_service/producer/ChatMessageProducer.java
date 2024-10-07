package com.webchat.chatapp.chat_service.producer;

import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageProducer {
    private final KafkaTemplate<String, Object> template;
    public void sendChatEventsToTopic(ChatMessageDTO chatMessageDTO){
        log.info("ChatMessageService : sendChatEventsToTopic ");
        log.info("Sending message to topic {}", chatMessageDTO.getChatId());
        try{
            CompletableFuture<SendResult<String, Object>> future = template.send("topic-web-chat-app", UUID.randomUUID().toString(), chatMessageDTO);
            future.whenComplete((result, ex) -> {
                if(ex == null){
                    log.info("Sent message-[ {}, {}", chatMessageDTO.toString() + "] with offset=[", result.getRecordMetadata().offset() + "]");
                } else {
                    log.info("Unable to send message=[ {}, {}", chatMessageDTO.toString() + "] due to : ", ex.getMessage());
                }
            });
        } catch(Exception e){
            log.info("Chat not Send {}",e.getMessage());
        }
    }
}
