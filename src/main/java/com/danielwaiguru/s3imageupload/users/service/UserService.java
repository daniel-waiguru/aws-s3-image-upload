package com.danielwaiguru.s3imageupload.users.service;

import com.danielwaiguru.s3imageupload.users.models.User;
import com.danielwaiguru.s3imageupload.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
