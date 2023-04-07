package com.resteng.resteng.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    long cart_id;

    public long getId() {
        return cart_id;
    }

    public void setId(long id) {
        this.cart_id = id;
    }

    public Cart(long id) {
        this.cart_id = id;
    }

    public Cart() {
    }

}
