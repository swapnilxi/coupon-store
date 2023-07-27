package com.mscode.couponstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mscode.couponstore.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository< Product, Long> {
    List<Product> findAllByCat_Id(int id);
    //extends crudrepository and pageandsorting repository 
    
}
