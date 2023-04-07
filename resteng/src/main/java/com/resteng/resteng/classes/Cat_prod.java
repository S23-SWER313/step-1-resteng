package com.resteng.resteng.classes;

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
