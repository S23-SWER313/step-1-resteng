package com.resteng.resteng.classes.order;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import com.resteng.resteng.classes.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private LocalDate order_date;

    @ManyToAny
    User user;

    public Order() {
    }

    public Order(double quantity,LocalDate order_date) {
        this.quantity = quantity;
        this.order_date = order_date;
        
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

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
