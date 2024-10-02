package com.webchat.chatapp.user_service.repository;


import com.webchat.chatapp.user_service.entity.User;
import com.webchat.chatapp.user_service.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
