package com.resteng.resteng.classes.order_product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.resteng.resteng.classes.order.CostomerOrder;
import com.resteng.resteng.classes.products.Product;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Order_product {

    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    CostomerOrder order;
    @ManyToOne
    Product product;

    public Order_product(CostomerOrder order, Product product) {
        this.order = order;
        this.product = product;
    }

}
