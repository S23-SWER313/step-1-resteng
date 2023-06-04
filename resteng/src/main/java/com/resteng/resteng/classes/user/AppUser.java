package com.resteng.resteng.classes.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.entity.Role;
import com.resteng.resteng.classes.mainUser.MainUser;

import lombok.Getter;
import lombok.Setter;

@Table(name = "Customer")
@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "user_first_name")
    @Size(min = 2, message = "Category Name should have atleast 2 characters")
    private String user_first_name;

    @Column(name = "user_last_name")
    @Size(min = 2, message = "Category Name should have atleast 2 characters")
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

    @OneToOne()
    private BankAccount bankAccount;
    @OneToOne()
    private Cart cart;

    @OneToOne
    MainUser mainUser;

    public AppUser() {
    }

    public AppUser(@Size(min = 2, message = "Category Name should have atleast 2 characters") String user_first_name,
            @Size(min = 2, message = "Category Name should have atleast 2 characters") String user_last_name,
            @NotNull @NotBlank String user_email, @NotNull @NotBlank String user_country,
            @NotNull @NotBlank String user_state, @NotNull @NotBlank String user_city,
            @NotNull @NotBlank String user_address1, String user_address2, @NotNull @NotBlank String user_phone) {
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_email = user_email;
        this.user_country = user_country;
        this.user_state = user_state;
        this.user_city = user_city;
        this.user_address1 = user_address1;
        this.user_address2 = user_address2;
        this.user_phone = user_phone;
    }

}