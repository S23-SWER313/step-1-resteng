package com.resteng.resteng.classes.categorie;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorie")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("")
    public ResponseEntity<Iterable<Categorie>> getAllCategories() {
        Iterable<Categorie> categories = categorieService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categorieId}")
    public ResponseEntity<Categorie> getCategoryById(@PathVariable String categorieId) {
        Categorie categorie = categorieService.getCategoryById(categorieId);
        if (categorie != null) {
            return new ResponseEntity<>(categorie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Categorie> addCategory(@Valid @RequestBody Categorie categorie) {
        Categorie savedCategorie = categorieService.addCategory(categorie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categorieId}").buildAndExpand(savedCategorie.getCategorie_title())
        .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{categorieId}")
    public ResponseEntity<Categorie> updateCategory(@PathVariable String categorieId,@Valid @RequestBody Categorie updatedCategorie) {
        Categorie categorie = categorieService.updateCategory(categorieId, updatedCategorie);
        if (categorie != null) {
            return new ResponseEntity<>(categorie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categorieId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable String categorieId) {
        categorieService.deleteCategory(categorieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

