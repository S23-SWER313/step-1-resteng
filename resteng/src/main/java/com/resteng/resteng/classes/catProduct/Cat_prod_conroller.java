package com.resteng.resteng.classes.catProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resteng.resteng.classes.products.Product;

@RestController
@RequestMapping("/api/v1")
public class Cat_prod_conroller {

    @Autowired
    private Cat_prod_service cat_prod_service;

    @GetMapping("/{catId}/products")
    public ResponseEntity<List<Product>> getAllProductsForCategory(@PathVariable String catId) {
        List<Product> products = cat_prod_service.getAllProductsForCategory(catId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/{catId}/products/{prodId}")
    public ResponseEntity<Product> addProductToCategory(@PathVariable String catId, @PathVariable Long prodId) {
        Product product = cat_prod_service.addProductToCategory(catId, prodId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
