package com.mscode.couponstore.controllers;
import com.mscode.couponstore.dto.ProductDTO;
import com.mscode.couponstore.model.Product;
import com.mscode.couponstore.service.ProductService;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;

import org.springframework.web.bind.annotation.*;
import com.mscode.couponstore.model.Category;
import com.mscode.couponstore.service.CategoryService;
import org.springframework.web.multipart.MultipartFile;

//controller for product and category
@Controller
public class Admin {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/java/resources/productImages";

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @RequestMapping("/admin")
    public String Admindashboard() {
        System.out.println("opening Admin dashboard");
        return "admin";
    }

    @RequestMapping(value = "/profileDemo", method = RequestMethod.GET)
    public String Namedemo(Model model) {
        System.out.println("passing name data using model");
        model.addAttribute("name", "adminDemo");
        model.addAttribute("category", "demoCategory");
        model.addAttribute("brandName", "demoBrand");
        return "profileDemo";
    }

    //<--categories-->

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String getCat(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping(value = "/admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category", new Category());//sending object
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    //reciving object
    public String postCatAdd(@ModelAttribute("category") Category category, Model model) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";

    }


    //delete
    @GetMapping("/admin/categories/delete/{id}")
    public String DeleteCat(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    //update
    @GetMapping("/admin/categories/update/{id}")
    public String UpdateCat(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            //to get the data from option we use .get
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    //<--products-->
    @GetMapping(value = "admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProduct());//sending object
        return "products";
    }

    @GetMapping(value = "admin/products/add")
    public String ProductAddGEt(Model model) {
        model.addAttribute("productsDTO", new ProductDTO());//sending object
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String ProductAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile imgFile,
                                 @RequestParam("imgName") String p_imageName) throws IOException {

        Product product = new Product();
        product.setP_id(productDTO.getId());
        product.setP_name(productDTO.getP_name());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setP_description(productDTO.getP_description());
        String imageUUID;
        if (!imgFile.isEmpty()) {
            imageUUID = imgFile.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, imgFile.getBytes());
        } else {
            imageUUID = p_imageName;
        }
        product.setP_imageName(imageUUID);
        productService.addProducts(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable long id, Model model) {
        Product product = productService.getProductbyId(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getP_id());
        productDTO.setP_name(product.getP_name());
        productDTO.setCategoryId((product.getCategory().getCat_Id()));
        productDTO.setPrice(product.getPrice());
        productDTO.setP_description(product.getP_description());
        productDTO.setP_imageName(product.getP_imageName());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";


    }
}
