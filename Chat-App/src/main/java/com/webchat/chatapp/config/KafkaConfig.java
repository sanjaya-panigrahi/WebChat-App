package com.webchat.chatapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webchat.chatapp.chat_service.consumer.ChatMessageDeserializer;
import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import com.webchat.chatapp.chat_service.producer.ChatMessageSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public Serializer<ChatMessageDTO> chatMessageSerializer() {
        return new ChatMessageSerializer();
    }

    @Bean
    public Deserializer<ChatMessageDTO> chatMessageDeserializer() {
        return new ChatMessageDeserializer();
    }
}
