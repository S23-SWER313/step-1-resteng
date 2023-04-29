package com.resteng.resteng.classes.categorie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
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
