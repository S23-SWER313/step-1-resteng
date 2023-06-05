package com.resteng.resteng.classes.cartItem;

import javax.persistence.ManyToOne;

import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.products.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDto {
    long quantity;

    @ManyToOne
    long product_id;

    @ManyToOne
    long cart__id;
}
