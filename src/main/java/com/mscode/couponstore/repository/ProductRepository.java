package com.mscode.couponstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mscode.couponstore.model.Product;
public interface ProductRepository extends JpaRepository< Product, Integer> {
    //extends crudrepository and pageandsorting repository 
    
}
