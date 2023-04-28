package com.resteng.resteng.classes.order_product;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.resteng.resteng.classes.products.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_product {

    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    Order order;
    @ManyToOne
    Product product;
}
