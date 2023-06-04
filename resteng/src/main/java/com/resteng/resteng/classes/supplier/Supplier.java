package com.resteng.resteng.classes.supplier;

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
import com.resteng.resteng.classes.entity.Role;
import com.resteng.resteng.classes.mainUser.MainUser;

import lombok.Getter;
import lombok.Setter;

@Table(name = "supplier")
@Entity
@Getter
@Setter
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

    @OneToOne()
    private BankAccount bankAccount;

    @OneToOne()
    private MainUser mainUser;

    public Supplier() {
    }

    public Supplier(
            @Size(min = 2, message = "Category Name should have atleast 2 characters") String supplier_first_name,
            @Size(min = 2, message = "Category Name should have atleast 2 characters") String supplier_last_name,
            @NotNull @NotBlank String supplier_email, @NotNull @NotBlank String supplier_country,
            @NotNull @NotBlank String supplier_state, @NotNull @NotBlank String supplier_city,
            @NotNull @NotBlank String supplier_address1, String supplier_address2,
            @NotNull @NotBlank String supplier_phone) {
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

}
