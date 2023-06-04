package com.resteng.resteng.classes.order;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.resteng.resteng.classes.user.AppUser;

@Table(name = "CostomerOrder")
@Entity
public class CostomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "quantity")
    @Positive
    private double quantity;

    @Column(name = "order_date")
    @NotNull
    private LocalDate order_date;

    @ManyToOne
    AppUser user;

    public CostomerOrder() {
    }

    public CostomerOrder(double quantity, LocalDate order_date) {
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

}
