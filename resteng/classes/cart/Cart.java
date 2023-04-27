package com.resteng.resteng.classes.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    Long cart_id;

    public Long getId() {
        return cart_id;
    }

    public void setId(Long id) {
        this.cart_id = id;
    }

    public Cart(Long id) {
        this.cart_id = id;
    }

    public Cart() {
    }

}
