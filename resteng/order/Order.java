package com.resteng.resteng.order;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "order")
@Entity

public class Order {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "order_date")
    private Date order_date;


    // getters and setters

    @ManyToMany()
    private Products products;
    @OneToMany()
    private Users users;


    public Order() {
    }

    public Order(Long order_id, Long product_id, Long user_id, double quantity,
    Date order_date) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.quantity = quantity;
        this.order_date = order_date;
        
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Users getUsers() {
        return users;
        
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

   
}
