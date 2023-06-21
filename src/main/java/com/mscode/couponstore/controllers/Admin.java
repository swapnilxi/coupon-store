package com.mscode.couponstore.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mscode.couponstore.model.Category;
import com.mscode.couponstore.service.CategoryService;


@Controller
public class Admin {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/admin")
    public String Admindashboard(){
    System.out.println("opening Admin dashboard");
    return "admin";
    }
    @RequestMapping(value="/profileDemo", method=RequestMethod.GET)
    public String Namedemo(Model model) {
        System.out.println("passing name data using model");
        model.addAttribute("name", "adminDemo");
        model.addAttribute("category", "demoCategory");
        model.addAttribute("brandName", "demoBrand");
        return "profileDemo";
    }
    
    //<--categories-->

    @RequestMapping(value="/admin/categories", method=RequestMethod.GET)
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping(value="/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());//sending object
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    //reciving object
    public String postCatAdd(@ModelAttribute("category") Category category, Model model){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";

    }


    //delete
    @GetMapping("/admin/categories/delete/{id}")
    public String DeleteCat(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
    //update
    @GetMapping("/admin/categories/update/{id}")
    public String UpdateCat(@PathVariable int id, Model model){
        Optional<Category> category= categoryService.getCategoryById(id);
       if(category.isPresent()){
        //to get the data from option we use .get
        model.addAttribute("category", category.get());
        return "categoriesAdd";
       }else{
        return "404";
       }
    }

    //<--products-->
    @GetMapping(value="/admin/products")
    public String getProducts(Model model){
        model.addAttribute("category", new Category());//sending object
        return "products";
    }



    
    
    

}
