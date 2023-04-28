package com.resteng.resteng.classes.catProduct;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<Cat_prod,Long> {
    
}
