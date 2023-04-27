package com.resteng.resteng.classes.account;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private long account_id;
    private String account_email; 
    private String account_password;
    private String account_role;

    public Account(){
    }

    public Account(String account_email, String account_password, String account_role) {
        this.account_email = account_email;
        this.account_password = account_password;
        this.account_role = account_role;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public String getAccount_role() {
        return account_role;
    }

    public void setAccount_role(String account_role) {
        this.account_role = account_role;
    }
}
