package com.resteng.resteng.classes.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.resteng.resteng.classes.bankAccount.BankAccount;

@RestController
@RequestMapping("/api/v1/users")
public class UserControler {

    @Autowired
    MainService service;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = service.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<User> CreateNewUser(@RequestBody User user) {
        User user2 = service.newUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user2.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        User user = service.replaceUser(newUser, id);
        return new ResponseEntity<>(user, HttpStatus.valueOf(204));
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
    return service.getUserById(id);
    }

    @GetMapping("/{id}/bankAccount")
    BankAccount getAccountBankOfUserById(@PathVariable Long id) {
    return service.getAccountBankOfUserById(id);
    }

    @PostMapping("/{id}/bankAccount")
    BankAccount createUserBankAccountById(@PathVariable Long id, @RequestBody
    BankAccount bankAccount) {
    return service.createsBankAccountOfUserById(id, bankAccount);
    }

    @PutMapping("/{id}/bankAccount")
    BankAccount updateBankAccountOfUserById(@PathVariable Long id, @RequestBody
    BankAccount bankAccount) {
    return service.updateBankAccountOfUserById(id, bankAccount);
    }

    @DeleteMapping("/{id}/bankAccount")
    void deleteBankAccountOfUserById(@PathVariable Long id) {
    service.deleteUserAccountBank(id);
    }
}