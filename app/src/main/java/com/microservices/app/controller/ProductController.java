package com.microservices.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.app.dto.Product;
import com.microservices.app.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String productsView(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            Model model) {
        List<Product> products = productService.getProducts(category, search);
        System.out.println(products);
        model.addAttribute("products", products);
        return "products";
    }
    
    @GetMapping("/categories")
    public String getAllCategories(Model model) {
    	List<String> categories=productService.getAllCategories();
    	System.out.println(categories);
    	model.addAttribute("categories",categories);
        return "products";
    }
    @GetMapping("/recentAdds")
    public String recentAddedProducts(Model model) {
    	List<String> products=productService.recentProducts();
    	System.out.println(products);
    	return "index";
    }
    
}
