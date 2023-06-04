package com.resteng.resteng.classes.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Cart() {
    }

}
