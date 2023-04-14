package com.resteng.resteng.classes.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControler {
    
    @Autowired
    MainService service;

    @GetMapping(value = { "users", "users/" })
    public List<User> getAll() {
        return service.all();
    }

    @PostMapping(value = { "users", "users/" })
    public User CreateNewPatient(@RequestBody User user) {
        return service.newUser(user);
    }
}
