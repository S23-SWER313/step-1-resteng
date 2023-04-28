package com.resteng.resteng.classes.order;

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

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "order_date")
    private Date order_date;


    // getters and setters

    

    public Order(Long order_id, double quantity,
    Date order_date) {
        this.order_id = order_id;
        this.quantity = quantity;
        this.order_date = order_date;
        
    }

    public Order() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
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
