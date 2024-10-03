package com.webchat.chatapp.chat_service.consumer;

import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatMessageConsumer {


    @KafkaListener(topics="topic-web-chat-app", groupId = "topic-web-chat-app-grp", topicPartitions = {@TopicPartition(topic = "topic-web-chat-app", partitions = {"0"})})
    public void consume(ChatMessageDTO chatMessageDTO){
        log.info("Consumer1 consume the message {} ", chatMessageDTO.toString());
    }

}
