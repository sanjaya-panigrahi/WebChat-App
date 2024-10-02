package com.webchat.userapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.webchat.userapp.dto.UserRequest;
import com.webchat.userapp.entity.User;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GenericUtility {

    public static User mapDTOToEntity(UserRequest userRequest) {
        log.info("Requested User {}", userRequest);
        User user = new User();

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public static UserRequest mapEntityToDTO(User user) {
        UserRequest userRequest = new UserRequest();

        userRequest.setFirstName(user.getFirstName());
        userRequest.setLastName(user.getLastName());
        userRequest.setUserName(user.getUserName());
        userRequest.setPassword(user.getPassword());

        return userRequest;
    }

    public static String convertObjectToJSON(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Error Occurs {}", e.getOriginalMessage());
        }
        return null;
    }

}
