package com.resteng.resteng.classes.supplier;

import com.resteng.resteng.classes.bankAccount.BankAccount;

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

@Table(name = "supplier")
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplier_id;

    @Column(name = "supplier_first_name")
    @Size(min = 2, message = "Category Name should have atleast 2 characters")
    private String supplier_first_name;

    @Column(name = "supplier_last_name")
    @Size(min = 2, message = "Category Name should have atleast 2 characters")
    private String supplier_last_name;

    @Column(name = "supplier_email")
    @NotNull
    @NotBlank
    private String supplier_email;

    @Column(name = "supplier_country")
    @NotNull
    @NotBlank
    private String supplier_country;

    @Column(name = "supplier_state")
    @NotNull
    @NotBlank
    private String supplier_state;

    @Column(name = "supplier_city")
    @NotNull
    @NotBlank
    private String supplier_city;

    @Column(name = "supplier_address1")
    @NotNull
    @NotBlank
    private String supplier_address1;

    @Column(name = "supplier_address2")
    private String supplier_address2;

    @Column(name = "supplier_phone")
    @NotNull
    @NotBlank
    private String supplier_phone;

    @Column(name = "supplier_password")
    @NotNull
    @NotBlank
    private String supplier_password;

    @OneToOne()
    private BankAccount bankAccount;

    public Supplier() {
    }

    public Supplier(String supplier_first_name, String supplier_last_name, String supplier_email,
            String supplier_country,
            String supplier_state, String supplier_city, String supplier_address1, String supplier_address2,
            String supplier_phone, String supplier_password) {

        this.supplier_first_name = supplier_first_name;
        this.supplier_last_name = supplier_last_name;
        this.supplier_email = supplier_email;
        this.supplier_country = supplier_country;
        this.supplier_state = supplier_state;
        this.supplier_city = supplier_city;
        this.supplier_address1 = supplier_address1;
        this.supplier_address2 = supplier_address2;
        this.supplier_phone = supplier_phone;
        this.supplier_password = supplier_password;
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

    public String getSupplier_password() {
        return supplier_password;
    }

    public void setSupplier_password(String supplier_password) {
        this.supplier_password = supplier_password;
    }

}
