package com.resteng.resteng.classes.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    Supplier newSupplier(Supplier supplier) {
        Supplier newSupplier = supplierRepository.save(supplier);
        return newSupplier;
    }

    Supplier replaceSupplier(Supplier newSupplier, Long id) {
        Supplier updatedSupplier = supplierRepository.findById(id) //
                .map(supplier -> {
                    supplier.setSupplier_first_name(newSupplier.getSupplier_first_name());
                    supplier.setSupplier_last_name(newSupplier.getSupplier_last_name());
                    supplier.setSupplier_email(newSupplier.getSupplier_email());
                    supplier.setSupplier_country(newSupplier.getSupplier_country());
                    supplier.setSupplier_state(newSupplier.getSupplier_state());
                    supplier.setSupplier_city(newSupplier.getSupplier_city());
                    supplier.setSupplier_address1(newSupplier.getSupplier_address1());
                    supplier.setSupplier_address2(newSupplier.getSupplier_address2());
                    supplier.setSupplier_phone(newSupplier.getSupplier_phone());
                    return supplierRepository.save(supplier);
                }) //
                .orElseGet(() -> {
                    newSupplier.setSupplier_id(id);
                    return supplierRepository.save(newSupplier);
                });
        return updatedSupplier;
    }

    Supplier getSupplierById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier Supplier = supplierOptional.get();
            return Supplier;
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }
}
