package com.mscode.couponstore.dto;

import org.springframework.data.annotation.Id;

import com.mscode.couponstore.model.Category;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String p_name;
    private int categoryId;
    private double price;
    private String p_description;
    private String p_imageName;
    
}
