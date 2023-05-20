package com.mscode.couponstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mscode.couponstore.model.*;
public interface CategoryRepository extends JpaRepository< Category, Integer> {
    //extends crudrepository and pageandsorting repository 
    
}
