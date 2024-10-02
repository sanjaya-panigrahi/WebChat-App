package com.webchat.chatapp.user_service.service;

import com.webchat.chatapp.user_service.entity.User;
import com.webchat.chatapp.user_service.entity.Status;
import com.webchat.chatapp.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        var existingUser = repository.findById(user.getFirstName()).orElse(null);
        if (existingUser != null) {
            user.setStatus(Status.ONLINE);
            user.setPassword(existingUser.getPassword());
            user.setUserName(existingUser.getUserName());
            repository.save(user);
        }else{
            throw new RuntimeException("Please register your self");
        }

    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getFirstName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}