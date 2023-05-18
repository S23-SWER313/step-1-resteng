package com.resteng.resteng.classes.catProduct;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.categorie.Categorie;
import com.resteng.resteng.classes.categorie.CategorieRepo;
import com.resteng.resteng.classes.products.Product;
import com.resteng.resteng.classes.products.ProductRepo;

@Service
public class Cat_prod_service {
    Cat_productRepo cat_productRepo;
    ProductRepo productRepo;
    CategorieRepo categorieRepo;

    public Cat_prod_service(Cat_productRepo cat_productRepo, ProductRepo productRepo, CategorieRepo categorieRepo) {
        this.cat_productRepo = cat_productRepo;
        this.productRepo = productRepo;
        this.categorieRepo = categorieRepo;
    }

    public List<Product> getAllProductsForCategory(String categoryName) {
        List<Cat_prod> catProds = cat_productRepo.findAll();
        List<Product> products = catProds.stream()
                .filter(e -> e.getCategorie().getCategorie_title().equals(categoryName))
                .map(e -> e.getProduct()).collect(Collectors.toList());
        return products;
    }

    public Product addProductToCategory(String categoryName, Long productId) {
        Product product = productRepo.findById(productId).get();
        Categorie categorie = categorieRepo.findById(categoryName).get();
        Cat_prod cat_prod = new Cat_prod(categorie, product);
        cat_productRepo.save(cat_prod);
        return product;
    }
}