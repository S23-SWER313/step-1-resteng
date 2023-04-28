package com.resteng.resteng.classes.supplier;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    SupplierService service;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<Supplier>> getAllSuppliers() {
        Iterable<Supplier> sups = service.getAllSuppliers();
        return new ResponseEntity<>(sups, HttpStatus.OK);
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Supplier> CreateNewSupplier(@RequestBody Supplier supplier) {
        Supplier supplier2 = service.newSupplier(supplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(supplier2.getSupplier_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier newSupplier, @PathVariable Long id) {
        Supplier supplier = service.replaceSupplier(newSupplier, id);
        return new ResponseEntity<>(supplier, HttpStatus.valueOf(204));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSuppluerById(@PathVariable Long id) {
        Supplier supplier = service.getSupplierById(id);
        if (supplier != null) {
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
