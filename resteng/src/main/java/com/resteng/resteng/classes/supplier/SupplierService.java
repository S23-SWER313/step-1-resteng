package com.resteng.resteng.classes.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.bankAccount.BankRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;
    private BankRepo bankRepo;

    public SupplierService(SupplierRepository supplierRepository, BankRepo bankRepo) {
        this.supplierRepository = supplierRepository;
        this.bankRepo = bankRepo;
    }

    List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    Supplier newSupplier(Supplier supplier) {
        Supplier newSupplier = supplierRepository.save(supplier);
        return newSupplier;
    }

    Supplier replaceSupplier(Supplier newSupplier, Long id) {
        Supplier updatedSupplier = supplierRepository.findById(id) //
                .map(supplier -> {
                    supplier.setSupplier_first_name(newSupplier.getSupplier_first_name());
                    supplier.setSupplier_last_name(newSupplier.getSupplier_last_name());
                    supplier.setSupplier_email(newSupplier.getSupplier_email());
                    supplier.setSupplier_country(newSupplier.getSupplier_country());
                    supplier.setSupplier_state(newSupplier.getSupplier_state());
                    supplier.setSupplier_city(newSupplier.getSupplier_city());
                    supplier.setSupplier_address1(newSupplier.getSupplier_address1());
                    supplier.setSupplier_address2(newSupplier.getSupplier_address2());
                    supplier.setSupplier_phone(newSupplier.getSupplier_phone());
                    supplier.setSupplier_password(newSupplier.getSupplier_password());
                    return supplierRepository.save(supplier);
                }) //
                .orElseGet(() -> {
                    newSupplier.setSupplier_id(id);
                    return supplierRepository.save(newSupplier);
                });
        return updatedSupplier;
    }

    Supplier getSupplierById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier Supplier = supplierOptional.get();
            return Supplier;
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    BankAccount getAccountBankOfSupplierById(Long id) {
        Optional<Supplier> SupplierOptional = supplierRepository.findById(id);
        if (SupplierOptional.isPresent()) {
            Supplier Supplier = SupplierOptional.get();
            return Supplier.getBankAccount();
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " has't bank account");
        }
    }

    private BankAccount createsBankAccount(BankAccount bankAccount) {
        return bankRepo.save(bankAccount);
    }

    BankAccount createsBankAccountOfSupplierById(Long id, BankAccount bankAccount) {
        BankAccount bankAccount2 = createsBankAccount(bankAccount);
        Optional<Supplier> SupplierOptional = supplierRepository.findById(id);
        if (SupplierOptional.isPresent()) {
            Supplier newSupplier = SupplierOptional.get();
            newSupplier.setBankAccount(bankAccount2);
            replaceSupplier(newSupplier, id);
            return newSupplier.getBankAccount();
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    private BankAccount updateBankAccount(Long id, BankAccount bankAccount) {
        BankAccount updatedBankAccount = bankRepo
                .findById(supplierRepository.findById(id).get().getBankAccount().getBank_account_id()) //
                .map(acc -> {
                    acc.setBank_account_balance(bankAccount.getBank_account_balance());
                    acc.setBank_account_number(bankAccount.getBank_account_number());
                    return bankRepo.save(acc);
                }) //
                .orElseGet(() -> {
                    return bankRepo.save(bankAccount);
                });
        return updatedBankAccount;
    }

    BankAccount updateBankAccountOfSupplierById(Long id, BankAccount account) {
        BankAccount account2 = updateBankAccount(id, account);
        Optional<Supplier> SupplierOptional = supplierRepository.findById(id);
        if (SupplierOptional.isPresent()) {
            Supplier Supplier = SupplierOptional.get();
            Supplier.setBankAccount(account2);
            replaceSupplier(Supplier, id);
            return account;
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    void deleteSupplierAccountBank(Long id) {
        Optional<Supplier> SupplierOptional = supplierRepository.findById(id);
        if (SupplierOptional.isPresent()) {
            Supplier Supplier = SupplierOptional.get();
            Supplier.setBankAccount(null);
            replaceSupplier(Supplier, id);
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    public BankAccount getAccountBankOfSuppluerById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            return supplier.getBankAccount();
        } else {
            throw new EntityNotFoundException("User with id " + id + " has't bank account");
        }
    }

    public BankAccount createsBankAccountOfSuppluerById(Long id, BankAccount bankAccount) {
        BankAccount bankAccount2 = createsBankAccount(bankAccount);
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier newSupplier = supplierOptional.get();
            newSupplier.setBankAccount(bankAccount2);
            replaceSupplier(newSupplier, id);
            return newSupplier.getBankAccount();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public BankAccount updateBankAccountOfSuppluerById(Long id, BankAccount bankAccount) {
        BankAccount account2 = updateBankAccount(id, bankAccount);
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier user = supplierOptional.get();
            user.setBankAccount(account2);
            replaceSupplier(user, id);
            return bankAccount;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public void deleteSuppluerAccountBank(Long id) {
        Optional<Supplier> SupplierOptional = supplierRepository.findById(id);
        if (SupplierOptional.isPresent()) {
            Supplier Supplier = SupplierOptional.get();
            Supplier.setBankAccount(null);
            replaceSupplier(Supplier, id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }
}
