package com.resteng.resteng.classes.user;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.mainUser.MainUser;
import com.resteng.resteng.classes.mainUser.MainUserServece;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/users")
public class UserControler {

    @Autowired
    MainService service;
    @Autowired
    MainUserServece mainUserServece;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<AppUser>> getAllUsers() {
        Iterable<AppUser> users = service.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = { "{mainUserId}", "/{mainUserId}" })
    public ResponseEntity<AppUser> CreateNewUser(@Valid @RequestBody AppUser user, @PathVariable long mainUserId) {
        MainUser mainUser = mainUserServece.getUserById(mainUserId);
        user.setMainUser(mainUser);
        Cart cart = new Cart();
        user.setCart(cart);
        AppUser user2 = service.newUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user2.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@Valid @RequestBody AppUser newUser, @PathVariable Long id) {
        AppUser user = service.replaceUser(newUser, id);
        return new ResponseEntity<>(user, HttpStatus.valueOf(204));
    }

    @GetMapping("/{id}")
    AppUser getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/{id}/bankAccount")
    BankAccount getAccountBankOfUserById(@PathVariable Long id) {
        return service.getAccountBankOfUserById(id);
    }

    @PostMapping("/{id}/bankAccount")
    ResponseEntity<BankAccount> createUserBankAccountById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        BankAccount bankAccount2 = service.createsBankAccountOfUserById(id, bankAccount);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/bankAccount")
                .buildAndExpand(bankAccount2.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/bankAccount")
    BankAccount updateBankAccountOfUserById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        return service.updateBankAccountOfUserById(id, bankAccount);
    }

    @DeleteMapping("/{id}/bankAccount")
    void deleteBankAccountOfUserById(@PathVariable Long id) {
        service.deleteUserAccountBank(id);
    }
}
