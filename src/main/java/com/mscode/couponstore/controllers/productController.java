package com.mscode.couponstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class productController {
    @RequestMapping("/products")
    public String productsList(){
        return "products";
    }
}
