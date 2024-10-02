package com.webchat.userapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;
}
