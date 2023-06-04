package com.resteng.resteng.classes.categorie;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
  @Id
  @Size(min=2, message="Category Name should have atleast 2 characters")
  String categorie_title; 
}
