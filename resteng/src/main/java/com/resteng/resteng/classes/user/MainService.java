package com.resteng.resteng.classes.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MainService {

    private UserRepository userRepository;

    public MainService(com.resteng.resteng.classes.user.UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> all() {
        return userRepository.findAll();
    }
    
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
}
