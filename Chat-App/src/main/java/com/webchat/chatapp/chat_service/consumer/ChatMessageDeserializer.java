package com.webchat.chatapp.chat_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class ChatMessageDeserializer implements Deserializer<ChatMessageDTO> {

    private static final String UTF_8 = "UTF-8";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatMessageDeserializer() {
    }


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Empty implementation
    }

    @Override
    public ChatMessageDTO deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, UTF_8), ChatMessageDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to ChatMessageDTO", e);
        }
    }

    @Override
    public void close() {
        // Empty implementation
    }
}
