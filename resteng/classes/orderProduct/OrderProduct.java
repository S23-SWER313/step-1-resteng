package com.resteng.resteng.classes.orderProduct;

import com.resteng.resteng.classes.order.Order;
import com.resteng.resteng.classes.products.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue
    long order_product_id;

    @ManyToOne
    Product product;

    @ManyToOne
    Order order;

    

    public OrderProduct() {
    }

    public OrderProduct(Product product, Order order) {
        this.product = product;
        this.order = order;
    }

    public long getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(long order_product_id) {
        this.order_product_id = order_product_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
