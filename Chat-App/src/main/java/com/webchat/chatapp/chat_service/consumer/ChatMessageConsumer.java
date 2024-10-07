package com.webchat.chatapp.chat_service.consumer;

import com.webchat.chatapp.chat_service.dto.ChatMessageDTO;
import com.webchat.chatapp.chat_service.entity.ChatNotification;
import com.webchat.chatapp.chat_service.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageConsumer {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @KafkaListener(topics="topic-web-chat-app", groupId = "topic-web-chat-app-grp")
    public void consumeChatEventsFromTopicANDNotify(ConsumerRecord<String, ChatMessageDTO> consumerRecord) {
        String key = consumerRecord.key();
        ChatMessageDTO chatMessageDTO = consumerRecord.value();
        log.info("Avro message received for key : {} value : {}", key, chatMessageDTO.toString());

        messagingTemplate.convertAndSendToUser(
            chatMessageDTO.getRecipientId().toString(), "/queue/messages",
            new ChatNotification(
                    chatMessageDTO.getId(),
                    chatMessageDTO.getSenderId().toString(),
                    chatMessageDTO.getRecipientId().toString(),
                    chatMessageDTO.getContent().toString()
            )
        );

        log.info("CHAT History {}", chatMessageService.findChatMessages(chatMessageDTO.getSenderId().toString(), chatMessageDTO.getRecipientId().toString()));
    }


}
