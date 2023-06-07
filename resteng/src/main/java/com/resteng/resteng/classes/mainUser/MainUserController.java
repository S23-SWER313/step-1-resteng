package com.resteng.resteng.classes.mainUser;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.resteng.resteng.classes.supplier.Supplier;
import com.resteng.resteng.classes.user.AppUser;
import com.resteng.resteng.classes.user.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/mainusers")
public class MainUserController {

    @Autowired
    MainUserServece service;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<MainUser>> getAllUsers() {
        Iterable<MainUser> users = service.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<MainUser> CreateNewUser(@Valid @RequestBody MainUserDto mainUserDto) {
        MainUser user = new MainUser(mainUserDto.getUser_password(), mainUserDto.getUsername());
        MainUser user2 = service.newUser(user, mainUserDto.getRole());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user2.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    MainUser getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("roleId/{username}")
    long getUserRoleId(@PathVariable String username) {
        return service.getUserRoleId(username);
    }

    @GetMapping("name/{username}")
    AppUser getUserByUserName(@PathVariable String username) {
        return service.getMainUserByUserName(username);
    }

    @GetMapping("supplier/{username}")
    Supplier getSuppByUserName(@PathVariable String username) {
        return service.getSuppByUserName(username);
    }

}
