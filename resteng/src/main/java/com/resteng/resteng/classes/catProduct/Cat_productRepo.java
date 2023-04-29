package com.resteng.resteng.classes.catProduct;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cat_productRepo extends JpaRepository<Cat_prod,Long> {
    
}
