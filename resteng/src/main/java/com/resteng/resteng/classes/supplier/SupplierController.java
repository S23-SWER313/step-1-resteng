package com.resteng.resteng.classes.supplier;

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
import com.resteng.resteng.classes.mainUser.MainUser;
import com.resteng.resteng.classes.mainUser.MainUserServece;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    SupplierService service;
    @Autowired
    MainUserServece mainUserServece;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<Supplier>> getAllSuppliers() {
        Iterable<Supplier> sups = service.getAllSuppliers();
        return new ResponseEntity<>(sups, HttpStatus.OK);
    }

    @PostMapping(value = { "{mainUserId}", "/{mainUserId}" })
    public ResponseEntity<Supplier> CreateNewSupplier(@Valid @RequestBody Supplier supplier, @PathVariable long mainUserId) {
        MainUser mainUser = mainUserServece.getUserById(mainUserId);
        supplier.setMainUser(mainUser);
        Supplier supplier2 = service.newSupplier(supplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(supplier2.getSupplier_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@Valid @RequestBody Supplier newSupplier, @PathVariable Long id) {
        Supplier supplier = service.replaceSupplier(newSupplier, id);
        return new ResponseEntity<>(supplier, HttpStatus.valueOf(204));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSuppluerById(@PathVariable Long id) {
        Supplier supplier = service.getSupplierById(id);
        if (supplier != null) {
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/bankAccount")
    BankAccount getAccountBankOfSuppluerById(@PathVariable Long id) {
        return service.getAccountBankOfSuppluerById(id);
    }

    @PostMapping("/{id}/bankAccount")
    ResponseEntity<BankAccount> createSuppluerBankAccountById(@PathVariable Long id,
            @RequestBody BankAccount bankAccount) {
        BankAccount bankAccount2 = service.createsBankAccountOfSuppluerById(id, bankAccount);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/bankAccount")
                .buildAndExpand(bankAccount2.getBank_account_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/bankAccount")
    BankAccount updateBankAccountOfSuppluerById(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        return service.updateBankAccountOfSuppluerById(id, bankAccount);
    }

    @DeleteMapping("/{id}/bankAccount")
    void deleteBankAccountOfSuppluerById(@PathVariable Long id) {
        service.deleteSuppluerAccountBank(id);
    }

}
