package com.resteng.resteng.classes.categorie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
  @Id
    @NotNull(message = "Categorie title cannot be null")
    @Size(min = 2, message = "Categorie title should have at least 2 characters")
  
  String categorie_title; 
}
