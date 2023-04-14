package com.resteng.resteng.classes.bankAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private long bank_account_id;
    private int bank_account_number;
    private double bank_account_balance;

    public BankAccount() {
    }

    public BankAccount(int bank_account_number, double bank_account_balance) {
        this.bank_account_number = bank_account_number;
        this.bank_account_balance = bank_account_balance;
    }

    public long getBank_account_id() {
        return bank_account_id;
    }

    public void setBank_account_id(long bank_account_id) {
        this.bank_account_id = bank_account_id;
    }

    public int getBank_account_number() {
        return this.bank_account_number;
    }

    public void setBank_account_number(int bank_account_number) {
        this.bank_account_number = bank_account_number;
    }

    public double getBank_account_balance() {
        return bank_account_balance;
    }

    public void setBank_account_balance(double bank_account_balance) {
        this.bank_account_balance = bank_account_balance;
    }

}
