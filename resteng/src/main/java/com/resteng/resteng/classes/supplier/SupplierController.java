package com.resteng.resteng.classes.supplier;

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
import com.resteng.resteng.classes.user.MainService;

@RestController
@RequestMapping("/api/v1/users")
public class SupplierController {

    @Autowired
    MainService service;

    @GetMapping(value = { "", "/" })
    public List<Supplier> getAll() {
        return service.getAllSuppliers();
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Object> CreateNewUser(@RequestBody Supplier supplier) {
        Supplier supplier2 = service.newSupplier(supplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(supplier2.getSupplier_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody Supplier newSupplier, @PathVariable Long id) {
        Supplier supplier3 = service.getSupplierById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(supplier3.getSupplier_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Supplier supplier4 = service.getSupplierById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(supplier4.getSupplier_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier user = service.getSupplierById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUser_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/bankAccount")
    ResponseEntity<Object> getAccountBankOfSupplierById(@PathVariable Long id) {
        BankAccount bankaccount = service.getAccountBankOfSupplierById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bankaccount.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PostMapping("/{id}/bankAccount")
    ResponseEntity<Object> createSupplierBankAccountById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        BankAccount bankaccount2 = service.getAccountBankOfSupplierById(id);
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
    ResponseEntity<Object> deleteBankAccountOfSupplierById(@PathVariable Long id) {
        BankAccount bankaccount4 = service.getAccountBankOfSupplierById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bankaccount4.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/cart/products")
    ResponseEntity<Object> getSupplierCartProducts(@PathVariable Long id) {
        CartProductRepo cartProductRepo = service.getSupplierCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/cart/products/{productId}")
    ResponseEntity<Object> addSupplierCartProducts(@PathVariable Long id, @RequestBody Long productId) {
        CartProductRepo cartProductRepo1 = service.getSupplierCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo1.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/cart/products/{productId}")
    ResponseEntity<Object> deleteSupplierCartProduct(@PathVariable Long id, @PathVariable Long productId) {
        CartProductRepo cartProductRepo2 = service.getSupplierCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo2.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/cart/products/{productId}")
    ResponseEntity<Object> getSupplierCartProduct(@PathVariable Long id, @RequestBody Long productId) {
        CartProductRepo cartProductRepo3 = service.getSupplierCartProduct(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartProductRepo3.getReferenceById(id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
