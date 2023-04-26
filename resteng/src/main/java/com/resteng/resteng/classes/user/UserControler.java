package com.resteng.resteng.classes.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.resteng.resteng.classes.account.Account;
import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.cartItem.CartItem;
import com.resteng.resteng.classes.cartProduct.CartProductRepo;
import com.resteng.resteng.classes.products.Product;

@RestController
@RequestMapping("/api/v1/users")
public class UserControler {

    @Autowired
    MainService service;

    @GetMapping(value = { "", "/" })
    public List<User> getAll() {
        return service.getAllUser();
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Object> CreateNewUser(@RequestBody User user) {
        User user2 = service.newUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user2.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        User user3 = service.getUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user3.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        User user4 = service.getUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user4.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = service.getUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/account")
    ResponseEntity<Object> getUserAccountById(@PathVariable Long id) {
        Account account = service.getAccountOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account.getAccount_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/account")
    ResponseEntity<Object> createUserAccountById(@PathVariable Long id, @RequestBody Account account) {
        Account account2 = service.getAccountOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account2.getAccount_id())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}/account")
    ResponseEntity<Object> updateUserAccountById(@PathVariable Long id, @RequestBody Account account) {
        Account account3 = service.getAccountOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account3.getAccount_id())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}/bankAccount")
    ResponseEntity<Object> getAccountBankOfUserById(@PathVariable Long id) {
        BankAccount bankaccount = service.getAccountBankOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bankaccount.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PostMapping("/{id}/bankAccount")
    ResponseEntity<Object> createUserBankAccountById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        BankAccount bankaccount2 = service.getAccountBankOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bankaccount2.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/bankAccount")
    ResponseEntity<Object> updateBankAccountOfUserById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        BankAccount bankaccount3 = service.getAccountBankOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bankaccount3.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/bankAccount")
    ResponseEntity<Object> deleteBankAccountOfUserById(@PathVariable Long id) {
        BankAccount bankaccount4 = service.getAccountBankOfUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bankaccount4.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/cart/products")
    ResponseEntity<Object> getUserCartProducts(@PathVariable Long id) {
        CartProductRepo cartProductRepo = service.getUserCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/cart/products/{productId}")
    ResponseEntity<Object> addUserCartProducts(@PathVariable Long id, @RequestBody Long productId) {
        CartProductRepo cartProductRepo1 = service.getUserCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo1.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/cart/products/{productId}")
    ResponseEntity<Object> deleteUserCartProduct(@PathVariable Long id, @PathVariable Long productId) {
        CartProductRepo cartProductRepo2 = service.getUserCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo2.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/cart/products/{productId}")
    ResponseEntity<Object> getUserCartProduct(@PathVariable Long id, @RequestBody Long productId) {
        CartProductRepo cartProductRepo3 = service.getUserCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo3.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
