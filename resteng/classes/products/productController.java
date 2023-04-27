package com.resteng.resteng.classes.products;

import java.util.List;

import com.resteng.resteng.classes.categorie.Categorie;

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

    // DELETE /products/{productId}
    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET /products/{product_id}/categories
    @GetMapping("/{productId}/categories")
    public ResponseEntity<Iterable<Categorie>> getProductCategories(@PathVariable long productId) {
        List<Categorie> categories = productService.getProductCategories(productId);
        if (categories != null) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /products/{product_id}/categories/{categorie_name}
    @GetMapping("/{productId}/categories/{categorieName}")
    public ResponseEntity<Categorie> getProductCategoryByName(@PathVariable long productId,
            @PathVariable String categorieName) {
        if (productService.getProductCategoryByName(productId, categorieName) != null) {
            return new ResponseEntity<>(productService.getProductCategoryByName(productId, categorieName),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /products/{product_id}/categories
    @PostMapping("/{productId}/categories")
    public ResponseEntity<Categorie> addProductCategory(@PathVariable long productId,
            @RequestBody Categorie categorie) {
        if (productService.addProductCategory(productId, categorie) != null) {
            return new ResponseEntity<>(productService.addProductCategory(productId, categorie), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT /products/{product_id}/categories/{categorie_name}
    @PutMapping("/{productId}/categories/{categorieName}")
    public ResponseEntity<Categorie> updateProductCategory(@PathVariable long productId, @PathVariable String categorieName,
                                      @RequestBody Categorie categorie) {
        Categorie categorie2 =   productService.updateProductCategory(productId, categorieName, categorie);
        if(categorie2 != null) {
            return new ResponseEntity<>(categorie2, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /products/{product_id}/categories/{categorie_name}
    @DeleteMapping("/{productId}/categories/{categorieName}")
    public ResponseEntity<HttpStatus> deleteProductCategory(@PathVariable long productId, @PathVariable String categorieName) {
        productService.deleteProductCategory(productId, categorieName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
