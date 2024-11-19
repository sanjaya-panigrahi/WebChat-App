package com.webchat.chatapp.chat_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDTO {
  private Long id;
  private String chatId;
  private String senderId;
  private String recipientId;
  private String content;
  private Date timestamp;
}