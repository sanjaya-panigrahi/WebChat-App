package com.webchat.chatapp.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    private String userName;
    private String password;
    @Id
    private String firstName;
    private String lastName;
    private Status status;
}