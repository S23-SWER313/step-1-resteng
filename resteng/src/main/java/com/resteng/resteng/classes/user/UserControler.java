package com.resteng.resteng.classes.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resteng.resteng.classes.account.Account;
import com.resteng.resteng.classes.bankAccount.BankAccount;
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
    public ResponseEntity<User> CreateNewUser(@RequestBody User user) {
        User user2 = service.newUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user2.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return service.replaceUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    User deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/{id}/account")
    Account getUserAccountById(@PathVariable Long id) {
        return service.getAccountOfUserById(id);
    }

    @PostMapping("/{id}/account")
    Account createUserAccountById(@PathVariable Long id, @RequestBody Account account) {
        return service.createsAccountOfUserById(id, account);
    }

    @PutMapping("/{id}/account")
    Account updateUserAccountById(@PathVariable Long id, @RequestBody Account account) {
        return service.updateAccountOfUserById(id, account);
    }

    @GetMapping("/{id}/bankAccount")
    BankAccount getAccountBankOfUserById(@PathVariable Long id) {
        return service.getAccountBankOfUserById(id);
    }

    @PostMapping("/{id}/bankAccount")
    BankAccount createUserBankAccountById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        return service.createsBankAccountOfUserById(id, bankAccount);
    }

    @PutMapping("/{id}/bankAccount")
    Account updateBankAccountOfUserById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        return service.updateBankAccountOfUserById(id, bankAccount);
    }

    @DeleteMapping("/{id}/bankAccount")
    void deleteBankAccountOfUserById(@PathVariable Long id) {
        service.deleteUserAccountBank(id);
    }

    @GetMapping("/{id}/cart/products")
    List<Product> getUserCartProducts(@PathVariable Long id) {
        return service.getUserCartProducts(id);
    }

    @PostMapping("/{id}/cart/products/{productId}")
    Product addUserCartProducts(@PathVariable Long id, @RequestBody Long productId) {
        return service.addUserCartProduct(id, productId);
    }

    @DeleteMapping("/{id}/cart/products/{productId}")
    void deleteUserCartProduct(@PathVariable Long id, @PathVariable Long productId) {
        service.deleteUserCartProduct(id, productId);
    }

    @GetMapping("/{id}/cart/products/{productId}")
    Product getUserCartProduct(@PathVariable Long id, @RequestBody Long productId) {
        return service.getUserCartProduct(id,productId);
    }

}
