package com.resteng.resteng.classes.catProduct;

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
@NoArgsConstructor
public class Cat_prod {

    public Cat_prod(Categorie categorie, Product product) {
        this.categorie = categorie;
        this.product = product;
    }

    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    Categorie categorie;
    @ManyToOne
    Product product;
}
