package com.resteng.resteng.classes.order_product;

import com.resteng.resteng.classes.order.CostomerOrder;
import com.resteng.resteng.classes.products.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
