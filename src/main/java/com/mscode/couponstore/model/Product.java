package com.mscode.couponstore.model;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long p_id;
    private String p_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "cat_id", referencedColumnName="cat_id")
    private Category category;
    private double price;
    private String p_description;
    private String p_imageName;
    
}
