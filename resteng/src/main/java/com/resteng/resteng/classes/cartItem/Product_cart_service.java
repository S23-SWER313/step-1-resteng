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

    List<CartItem> getAllProductCart(Long cartId) {
        List<CartItem> list = catrItemRepo.findAll().stream()
                .filter(x -> x.getCart().getId() == cartId).toList();
        return list;
    }

    CartItem deleteCartItem(Long id) {
        CartItem cartItem = catrItemRepo.findById(id).get();
        catrItemRepo.delete(cartItem);
        return cartItem;
    }

}
