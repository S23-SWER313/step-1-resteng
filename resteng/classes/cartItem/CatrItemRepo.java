package com.resteng.resteng.classes.cartItem;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatrItemRepo extends JpaRepository<CartItem,Long> {
    
}
