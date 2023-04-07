package com.resteng.resteng.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    long cart_item_id;

    @ManyToOne
    Product product;

    @ManyToOne
    Cart cart;

    public long getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(long cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
