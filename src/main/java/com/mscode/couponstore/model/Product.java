package com.mscode.couponstore.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "cat_id" referencedColumnName="cat_id")
    private Category category;
    private double price;
    private String p_description;
    private String p_imageName;
    
}
