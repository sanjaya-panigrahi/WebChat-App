package com.webchat.userapp.service;


import com.webchat.userapp.dto.UserRequest;
import com.webchat.userapp.entity.Status;
import com.webchat.userapp.entity.User;
import com.webchat.userapp.repository.UserRepository;
import com.webchat.userapp.util.GenericUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String saveUser(UserRequest userRequest) {
        User user = GenericUtility.mapDTOToEntity(userRequest);
        user.setStatus(Status.OFFLINE);
        User userSaved = userRepository.save(user);
        log.info("The User Saved Successfully {}", userSaved);
        return userSaved.getUserName();
    }
}
