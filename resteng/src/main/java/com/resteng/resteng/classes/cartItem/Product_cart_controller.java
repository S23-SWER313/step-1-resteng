package com.resteng.resteng.classes.cartItem;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/productsCart")
public class Product_cart_controller {
    
    @Autowired
    private Product_cart_service product_cart_service;

    @PostMapping(value = { "", "/" })
    public ResponseEntity<CartItem> CreateNewCartItem(@RequestBody CartItem CartItem) {
        CartItem CartItem2 = product_cart_service.newCartItem(CartItem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(CartItem2.getCart_item_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
