package com.mscode.couponstore.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class categories {
@RequestMapping("/categories")
public String categorieslist(){
    System.out.println("categories");
    return "categories";
}

    
}
