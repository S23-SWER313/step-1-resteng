package com.resteng.resteng.classes.catProduct;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.products.Product;

@Service
public class Cat_prod_service {
    Cat_productRepo cat_productRepo;

    public Cat_prod_service(Cat_productRepo cat_productRepo) {
        this.cat_productRepo = cat_productRepo;
    }

    public List<Product> getAllProductsForCategory(String categoryName) {
        List<Cat_prod> catProds = cat_productRepo.findAll();
        List<Product> products = catProds.stream()
                .filter(e -> e.getCategorie().getCategorie_title().equals(categoryName))
                .map(e -> e.getProduct()).collect(Collectors.toList());
        return products;
    }
}