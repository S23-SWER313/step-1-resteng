package com.resteng.resteng.classes.products;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<Product> updateProduct(@PathVariable long productId, @Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(productId, product), HttpStatus.valueOf(204));
    }

    // POST /products
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@Valid @RequestParam Map<String, String> requestParams) {

        String product_img = requestParams.get("product_img");
        String product_title = requestParams.get("product_title");
        String product_area = requestParams.get("product_area");
        String product_description = requestParams.get("product_description");
        double product_price = Double.parseDouble(requestParams.get("product_price"));
        double product_quantity = Double.parseDouble(requestParams.get("product_quantity"));
        String type = requestParams.get("type");
        String supplier_supplier_id = requestParams.get("supplier_supplier_id");

        Product product = new Product(product_title, product_price, product_description, product_img, product_quantity,
                product_area, type);

        Product product2 = productService.addProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product2.getProduct_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
