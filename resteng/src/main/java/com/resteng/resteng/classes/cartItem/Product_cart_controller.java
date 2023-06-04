package com.resteng.resteng.classes.cartItem;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/productsCart")
public class Product_cart_controller {

    @Autowired
    private Product_cart_service product_cart_service;

    @PostMapping(value = { "carts/{cartId}/products/{productId}", "/carts/{cartId}/products/{productId}" })
    public ResponseEntity<CartItem> CreateNewCartItem(@PathVariable Long cartId, @PathVariable Long productId) {
        CartItem CartItem2 = product_cart_service.newCartItem(cartId, productId);

        if (CartItem2 != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(CartItem2.getCart_item_id())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = { "{cartId}", "/{cartId}" })
    public ResponseEntity<Iterable<CartItem>> getAllProductCart(@PathVariable Long cartId) {
        Iterable<CartItem> products = product_cart_service.getAllProductCart(cartId);
        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<HttpStatus> deleteProsuctFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        product_cart_service.deleteCartItem(cartId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
