package com.resteng.resteng.classes.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.catProduct.Cat_productRepo;
import com.resteng.resteng.classes.catProduct.Cat_prod;
import com.resteng.resteng.classes.categorie.Categorie;
import com.resteng.resteng.classes.supplier.Supplier;
import com.resteng.resteng.classes.supplier.SupplierService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;
    private Cat_productRepo cartProductRepo;
    private SupplierService supplierService;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(long id, Product updatedProduct) {
        Product product = getProductById(id);
        if (product != null) {
            product.setProduct_title(updatedProduct.getProduct_title());
            product.setProduct_price(updatedProduct.getProduct_price());
            product.setProduct_description(updatedProduct.getProduct_description());
            product.setProduct_img(updatedProduct.getProduct_img());
            product.setProduct_quantity(updatedProduct.getProduct_quantity());
            product.setProduct_area(updatedProduct.getProduct_area());
            return productRepo.save(product);
        } else {
            updatedProduct.setProduct_id(id);
            return productRepo.save(updatedProduct);
        }
    }

    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

    public List<Categorie> getProductCategories(long productId) {
        List<Cat_prod> catProds = cartProductRepo.findAll();
        List<Categorie> categories = catProds.stream()
                .filter(e -> e.getProduct().getProduct_id() == productId)
                .map(e -> e.getCategorie()).collect(Collectors.toList());

        if (!categories.isEmpty())
            return categories;
        else
            return null;
    }

    public Categorie getProductCategoryByName(long productId, String categorieName) {
        List<Cat_prod> catProds = cartProductRepo.findAll();
        List<Categorie> categories = catProds.stream()
                .filter(e -> e.getProduct().getProduct_id() == productId
                        && e.getCategorie().getCategorie_title() == categorieName)
                .map(e -> e.getCategorie()).collect(Collectors.toList());
        if (!categories.isEmpty())
            return categories.get(0);
        else
            return null;
    }

    public Categorie addProductCategory(long productId, Categorie categorie) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product != null) {
            Cat_prod catProd = new Cat_prod();
            catProd.setProduct(product);
            catProd.setCategorie(categorie);
            cartProductRepo.save(catProd);
            return categorie;
        } else {
            return null;
        }
    }

    public Categorie updateProductCategory(long productId, String categorieName, Categorie categorie) {
        List<Cat_prod> catProds = cartProductRepo.findAll().stream()
                .filter(e -> e.getCategorie().getCategorie_title() == categorieName
                        && e.getProduct().getProduct_id() == productId)
                .collect(Collectors.toList());
        if (catProds.size() > 0) {
            Cat_prod catProd = catProds.get(0);
            catProd.setCategorie(categorie);
            cartProductRepo.save(catProd);
            return categorie;
        } else {
            return null;
        }
    }

    public void deleteProductCategory(long productId, String categorieName) {
        Cat_prod catProds = cartProductRepo.findAll().stream()
                .filter(e -> e.getCategorie().getCategorie_title() == categorieName
                        && e.getProduct().getProduct_id() == productId)
                .collect(Collectors.toList()).get(0);
        cartProductRepo.deleteById(catProds.getId());
    }

    public Supplier getSupplier(long id) {
        return supplierService.getSupplierById(id);
    }

    public List<Product> getAllProductsForSupplier(long supplierId) {
        return productRepo.findAll().stream().filter(e -> e.getSupplier().getSupplier_id() == supplierId)
                .collect(Collectors.toList());
    }
}
