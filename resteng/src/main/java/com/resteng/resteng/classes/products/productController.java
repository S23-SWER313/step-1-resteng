package com.resteng.resteng.classes.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class productController {

    @Autowired
    private ProductService productService;

    // GET /products
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // GET /products/{productId}
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT /products/{productId}
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable long productId, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(productId, product), HttpStatus.valueOf(204));
    }

    // POST /products
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

}