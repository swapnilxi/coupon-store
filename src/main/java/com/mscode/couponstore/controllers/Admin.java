package com.mscode.couponstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class Admin {
    @RequestMapping("/admin")
    public String admindashboard(){
    System.out.println("opening Admin dashboard");
    return "admin";
    }
    @RequestMapping(value="/profileDemo", method=RequestMethod.GET)
    public String profileDemo(Model model) {
        System.out.println("passing name data using model");
        model.addAttribute("name", "adminDemo");
        model.addAttribute("category", "demoCategory");
        model.addAttribute("brandName", "demoBrand");
        return "profileDemo";
    }

    @RequestMapping(value="/admin/categories", method=RequestMethod.GET)
    public String showCat() {
        return "categories";
    }
    
    

}
