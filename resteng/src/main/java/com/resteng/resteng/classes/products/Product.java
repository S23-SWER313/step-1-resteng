package com.resteng.resteng.classes.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.resteng.resteng.classes.supplier.Supplier;

@Entity
public class Product {
    @Id
    @GeneratedValue
    long product_id;
    @Size(min = 2, message = "Category Name should have atleast 2 characters")
    String product_title;
    @Positive
    double product_price;
    String product_description;
    @Column(length = 99999999)
    String product_img;
    @Positive
    double product_quantity;
    @NotBlank
    String product_area;
    String type;

    @ManyToOne
    Supplier supplier;

    public Product(@Size(min = 2, message = "Category Name should have atleast 2 characters") String product_title,
            @Positive double product_price, String product_description, String product_img,
            @Positive double product_quantity, @NotBlank String product_area, String type) {
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_img = product_img;
        this.product_quantity = product_quantity;
        this.product_area = product_area;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

}
