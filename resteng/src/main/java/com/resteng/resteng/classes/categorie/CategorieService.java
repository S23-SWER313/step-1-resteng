package com.resteng.resteng.classes.categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {

    @Autowired
    CategorieRepo categorieRepo;

    public Iterable<Categorie> getAllCategories() {
        return categorieRepo.findAll();
    }

    public Categorie getCategoryById(String categorieId) {
        return categorieRepo.findById(categorieId).orElse(null);
    }

    public Categorie addCategory(Categorie categorie) {
        return categorieRepo.save(categorie);
    }

    public Categorie updateCategory(String categorieId, Categorie updatedCategorie) {
        Categorie existingCategorie = categorieRepo.findById(categorieId).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setCategorie_title(updatedCategorie.getCategorie_title());
            return categorieRepo.save(existingCategorie);
        } else {
            return null;
        }
    }

    public void deleteCategory(String categorieId) {
        categorieRepo.deleteById(categorieId);
    }
}

