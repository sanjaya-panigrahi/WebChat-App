package com.webchat.chatapp.chat_service.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import java.util.Map;

public class ChatMessageSerializer implements Serializer<ChatMessageDTO> {

    private static final String UTF_8 = "UTF-8";
    private final ObjectMapper objectMapper= new ObjectMapper();

    public ChatMessageSerializer() {

    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Empty implementation
    }

    @Override
    public byte[] serialize(String topic, ChatMessageDTO data) {
        try {
            if (data == null) {
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing ChatMessageDTO to byte[]", e);
        }
    }

    @Override
    public void close() {
        // Empty implementation
    }
}