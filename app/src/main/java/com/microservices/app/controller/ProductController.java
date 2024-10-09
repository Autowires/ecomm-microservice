package com.microservices.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String productsView(@RequestParam(required = false) String category,
			@RequestParam(required = false) String search, Model model) {
		List<Product> products = productService.getProducts(category, search);
		List<String> categories = productService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return "products";
	}

	@GetMapping("/{productId}")
	public String productinfo(@PathVariable int productId, Model model) {

		Object productinfo = productService.getproduct(productId);
		model.addAttribute("product", productinfo);
		model.addAttribute("reviews", productService.getProductReviews(productId));
		return "productinfo";

	}

}
