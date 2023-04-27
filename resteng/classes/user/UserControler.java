package com.resteng.resteng.classes.user;

import java.net.URI;
import java.util.List;

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

import com.resteng.resteng.classes.account.Account;
import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.products.Product;

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
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return service.replaceUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    User deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = service.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/account")
    public ResponseEntity<Account> getUserAccountById(@PathVariable Long id) {
        Account account = service.getAccountOfUserById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}/account")
    public ResponseEntity<Account> createNewUserAccount(@PathVariable Long id, @RequestBody Account account) {
        Account newAccount = service.createsAccountOfUserById(id, account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id} ")
                .buildAndExpand(newAccount.getAccount_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/account")
    Account updateUserAccountById(@PathVariable Long id, @RequestBody Account account) {
        return service.updateAccountOfUserById(id, account);
    }

    @GetMapping("/{id}/bankAccount")
    public ResponseEntity<BankAccount> getUserBankAccountById(@PathVariable Long id) {
        BankAccount bankAccount = service.getAccountBankOfUserById(id);
        if (bankAccount != null) {
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}/bankAccounts")
    public ResponseEntity<BankAccount> createUserBankAccountById(@PathVariable Long id,
            @RequestBody BankAccount bankAccount) {
        BankAccount newBankAccount = service.createsBankAccountOfUserById(id, bankAccount);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBankAccount.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
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
    public ResponseEntity<List<Product>> getUserCartProducts(@PathVariable Long id) {
        List<Product> products = service.getUserCartProducts(id);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}/cart/products/{productId}")
    public ResponseEntity<Product> addUserCartProducts(@PathVariable Long id, @RequestBody Long productId) {
        Product addedProduct = service.addUserCartProduct(id, productId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedProduct.getProduct_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/cart/products/{productId}")
    void deleteUserCartProduct(@PathVariable Long id, @PathVariable Long productId) {
        service.deleteUserCartProduct(id, productId);
    }

    @GetMapping("/{id}/cart/products/{productId}")
    public ResponseEntity<Product> getUserCartProduct(@PathVariable Long id, @PathVariable Long productId) {
        Product product = service.getUserCartProduct(id, productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
