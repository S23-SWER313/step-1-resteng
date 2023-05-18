package com.resteng.resteng.classes.user;

import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.cart.Cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "user_first_name")
    @Size(min=2, message="Category Name should have atleast 2 characters")
    private String user_first_name;

    @Column(name = "user_last_name")
    @Size(min=2, message="Category Name should have atleast 2 characters")
    private String user_last_name;

    @Column(name = "user_email")
    @NotNull
    @NotBlank
    private String user_email;

    @Column(name = "user_country")
    @NotNull
    @NotBlank
    private String user_country;

    @Column(name = "user_state")
    @NotNull
    @NotBlank
    private String user_state;

    @Column(name = "user_city")
    @NotNull
    @NotBlank
    private String user_city;

    @Column(name = "user_address1")
    @NotNull
    @NotBlank
    private String user_address1;

    @Column(name = "user_address2")
    private String user_address2;

    @Column(name = "user_phone")
    @NotNull
    @NotBlank
    private String user_phone;

    @Column(name = "user_password")
    @NotNull
    @NotBlank
    private String user_password;

    // getters and setters

    @OneToOne()
    private BankAccount bankAccount;
    @OneToOne()
    private Cart cart;

    public User() {
    }

    public User(String user_first_name, String user_last_name, String user_email, String user_country,
            String user_state, String user_city, String user_address1, String user_address2, String user_phone, String user_password) {
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_email = user_email;
        this.user_country = user_country;
        this.user_state = user_state;
        this.user_city = user_city;
        this.user_address1 = user_address1;
        this.user_address2 = user_address2;
        this.user_phone = user_phone;
        this.user_password = user_password;
    }

    

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_address1() {
        return user_address1;
    }

    public void setUser_address1(String user_address1) {
        this.user_address1 = user_address1;
    }

    public String getUser_address2() {
        return user_address2;
    }

    public void setUser_address2(String user_address2) {
        this.user_address2 = user_address2;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

}