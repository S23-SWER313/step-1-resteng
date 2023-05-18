package com.resteng.resteng.classes.cartItem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.cart.*;
import com.resteng.resteng.classes.products.*;

@Service
public class Product_cart_service {

    CatrItemRepo catrItemRepo;
    ProductRepo productRepo;
    CartRepo cartRepo;

    public Product_cart_service(CatrItemRepo catrItemRepo, ProductRepo productRepo, CartRepo cartRepo) {
        this.catrItemRepo = catrItemRepo;
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
    }

    public List<CartItem> getAll() {
        return catrItemRepo.findAll();
    }

    CartItem newCartItem(Long cartId, Long productId) {
<<<<<<< HEAD
        if (productRepo.findById(productId).isPresent()) {
            Product product = productRepo.findById(productId).get();
            if (cartRepo.findById(cartId).isPresent()) {
                Cart cart = cartRepo.findById(cartId).get();
                CartItem CartItem = new CartItem(product, cart);
                CartItem newCartItem = catrItemRepo.save(CartItem);
                return newCartItem;
            }
        }

        return null;

=======
        Product product = productRepo.findById(productId).get();
        Cart cart = cartRepo.findById(cartId).get();
        CartItem CartItem = new CartItem(product,cart);
        CartItem newCartItem = catrItemRepo.save(CartItem);
        return newCartItem;
>>>>>>> ffda62d01a1958b9dda436e55936ee60425f926f
    }

    List<CartItem> getAllProductCart(Long cartId) {
        List<CartItem> list = catrItemRepo.findAll().stream()
                .filter(x -> x.getCart().getId() == cartId).toList();
        return list;
    }

    CartItem deleteCartItem(Long cartId, Long productId) {
        CartItem cartItem = catrItemRepo.findAll().stream().filter(e-> e.getCart().getId() == cartId && e.getProduct().getProduct_id() == productId).findFirst().get();
        catrItemRepo.delete(cartItem);
        return cartItem;
    }

}
