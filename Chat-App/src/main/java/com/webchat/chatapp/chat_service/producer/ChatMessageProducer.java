package com.webchat.chatapp.chat_service.producer;

import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ChatMessageProducer {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendEventsToTopic(ChatMessageDTO chatMessageDTO){
        try{
            CompletableFuture<SendResult<String, Object>> future = template.send("topic-web-chat-app", chatMessageDTO);
            future.whenComplete((result, ex) -> {
                if(ex == null){
                    System.out.println("Sent message-[" + chatMessageDTO.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" + chatMessageDTO.toString() +
                            "] due to : " +ex.getMessage());
                }
            });
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
