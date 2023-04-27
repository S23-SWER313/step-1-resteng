package com.resteng.resteng.classes.cartProduct;

import com.resteng.resteng.classes.categorie.Categorie;
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
public class Cat_prod {

    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    Categorie categorie;
    @ManyToOne
    Product product;
}
