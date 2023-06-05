package com.resteng.resteng.classes.cartItem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.products.Product;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    long cart_item_id;

    long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    Cart cart;

    public CartItem() {
    }

    public CartItem(long quantity, Product product, Cart cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

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

    @Override
    public String toString() {
        return "CartItem [cart_item_id=" + cart_item_id + "]";
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}
