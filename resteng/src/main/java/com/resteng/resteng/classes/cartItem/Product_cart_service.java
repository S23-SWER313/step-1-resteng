package com.resteng.resteng.classes.cartItem;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Product_cart_service {
    
    CatrItemRepo catrItemRepo;

    public Product_cart_service(CatrItemRepo catrItemRepo) {
        this.catrItemRepo = catrItemRepo;
    }

    public List<CartItem> getAll() {
        return catrItemRepo.findAll();
    }

    CartItem newCartItem(CartItem CartItem) {
        CartItem newCartItem = catrItemRepo.save(CartItem);
        return newCartItem;
    }

}
