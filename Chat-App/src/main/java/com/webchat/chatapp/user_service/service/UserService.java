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
        var existingUser = repository.findByUserName(user.getUserName());
        if (existingUser != null) {
            user.setStatus(Status.ONLINE);
            user.setPassword(existingUser.getPassword());
            user.setLastName(existingUser.getLastName());
            user.setFirstName(existingUser.getFirstName());
            user.setUserName(existingUser.getUserName());
            repository.save(user);
        }else{
            throw new RuntimeException("Please register your self");
        }

    }

    public String getUser(String username) {
        User user = repository.findByUserName(username);
        return user.getFirstName() + " " + user.getLastName();
    }

    public void disconnect(User user) {
        var storedUser = repository.findByUserName(user.getFirstName());
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}