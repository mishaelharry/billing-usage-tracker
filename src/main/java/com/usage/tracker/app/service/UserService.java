package com.usage.tracker.app.service;

import com.usage.tracker.app.model.User;
import com.usage.tracker.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username){
        User user = new User();
        user.setUsername(username);
        user.setStatus(true);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
