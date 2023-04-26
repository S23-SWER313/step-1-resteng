package com.resteng.resteng.classes.cartProduct;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<Cat_prod,Long> {
    
}
