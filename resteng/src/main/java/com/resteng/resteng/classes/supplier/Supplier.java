package com.resteng.resteng.classes.supplier;

import com.resteng.resteng.classes.account.Account;
import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.cart.Cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "supplier")
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplier_id;

    @Column(name = "supplier_first_name")
    private String supplier_first_name;

    @Column(name = "supplier_last_name")
    private String supplier_last_name;

    @Column(name = "supplier_email")
    private String supplier_email;

    @Column(name = "supplier_country")
    private String supplier_country;

    @Column(name = "supplier_state")
    private String supplier_state;

    @Column(name = "supplier_city")
    private String supplier_city;

    @Column(name = "supplier_address1")
    private String supplier_address1;

    @Column(name = "supplier_address2")
    private String supplier_address2;

    @Column(name = "supplier_phone")
    private String supplier_phone;

    
    @OneToOne()
    private BankAccount bankAccount;
    @OneToOne()
    private Cart cart;

    public Supplier() {
    }

    public Supplier( String supplier_first_name, String supplier_last_name, String supplier_email, String supplier_country,
            String supplier_state, String supplier_city, String supplier_address1, String supplier_address2, String supplier_phone) {
            

                this.supplier_first_name = supplier_first_name;
        this.supplier_last_name = supplier_last_name;
        this.supplier_email = supplier_email;
        this.supplier_country = supplier_country;
        this.supplier_state = supplier_state;
        this.supplier_city = supplier_city;
        this.supplier_address1 = supplier_address1;
        this.supplier_address2 = supplier_address2;
        this.supplier_phone = supplier_phone;
    }

    public Long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_first_name() {
        return supplier_first_name;
    }

    public void setSupplier_first_name(String supplier_first_name) {
        this.supplier_first_name = supplier_first_name;
    }

    public String getSupplier_last_name() {
        return supplier_last_name;
    }

    public void setSupplier_last_name(String supplier_last_name) {
        this.supplier_last_name = supplier_last_name;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }

    public String getSupplier_country() {
        return supplier_country;
    }

    public void setSupplier_country(String supplier_country) {
        this.supplier_country = supplier_country;
    }

    public String getSupplier_state() {
        return supplier_state;
    }

    public void setSupplier_state(String supplier_state) {
        this.supplier_state = supplier_state;
    }

    public String getSupplier_city() {
        return supplier_city;
    }

    public void setSupplier_city(String supplier_city) {
        this.supplier_city = supplier_city;
    }

    public String getSupplier_address1() {
        return supplier_address1;
    }

    public void setSupplier_address1(String supplier_address1) {
        this.supplier_address1 = supplier_address1;
    }

    public String getSupplier_address2() {
        return supplier_address2;
    }

    public void setSupplier_address2(String supplier_address2) {
        this.supplier_address2 = supplier_address2;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    

}
