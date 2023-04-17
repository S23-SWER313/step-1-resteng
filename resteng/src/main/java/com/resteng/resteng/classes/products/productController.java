package com.resteng.resteng.classes.products;

import java.util.List;

import com.resteng.resteng.classes.categorie.Categorie;
import com.resteng.resteng.classes.products.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class productController {

    @Autowired
    private ProductService productService;

    // GET /products
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /products/{productId}
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
    }

    // PUT /products/{productId}
    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable long productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
    }

    // POST /products
    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // DELETE /products/{productId}
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }

    // GET /products/{product_id}/categories
    @GetMapping("/{productId}/categories")
    public List<Categorie> getProductCategories(@PathVariable long productId) {
        return productService.getProductCategories(productId);
    }

    // GET /products/{product_id}/categories/{categorie_name}
    @GetMapping("/{productId}/categories/{categorieName}")
    public Categorie getProductCategoryByName(@PathVariable long productId, @PathVariable String categorieName) {
        return productService.getProductCategoryByName(productId, categorieName);
    }

    // POST /products/{product_id}/categories
    @PostMapping("/{productId}/categories")
    public void addProductCategory(@PathVariable long productId, @RequestBody Categorie categorie) {
        productService.addProductCategory(productId, categorie);
    }

    // PUT /products/{product_id}/categories/{categorie_name}
    @PutMapping("/{productId}/categories/{categorieName}")
    public void updateProductCategory(@PathVariable long productId, @PathVariable String categorieName,
                                      @RequestBody Categorie categorie) {
        productService.updateProductCategory(productId, categorieName, categorie);
    }

    // DELETE /products/{product_id}/categories/{categorie_name}
    @DeleteMapping("/{productId}/categories/{categorieName}")
    public void deleteProductCategory(@PathVariable long productId, @PathVariable String categorieName) {
        productService.deleteProductCategory(productId, categorieName);
    }
}

