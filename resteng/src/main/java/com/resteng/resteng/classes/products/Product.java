package com.resteng.resteng.classes.products;

import com.resteng.resteng.classes.supplier.Supplier;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue
    long product_id;
    @Size(min=2, message="Category Name should have atleast 2 characters")
    String product_title;
    @Positive
    double product_price;
    String product_description;
    byte[] product_img;
    @Positive
    double product_quantity;
    @NotBlank
    String product_area;

    @ManyToOne
    Supplier supplier;

    public Product(long product_id, String product_title, double product_price, String product_description,
            byte[] product_img, double product_quantity, String product_area) {
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_img = product_img;
        this.product_quantity = product_quantity;
        this.product_area = product_area;
    }

    public Product() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public byte[] getProduct_img() {
        return product_img;
    }

    public void setProduct_img(byte[] product_img) {
        this.product_img = product_img;
    }

    public double getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(double product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_area() {
        return product_area;
    }

    public void setProduct_area(String product_area) {
        this.product_area = product_area;
    }

}
