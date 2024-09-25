package com.microservices.product.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> productsView(@RequestParam(required = false) String category,
			@RequestParam(required = false) String search) {
		return productService.getProducts(category, search);
	}
	@GetMapping("/category")
	public List<String> displayCategories(){
		return productService.getCategories();
		
	}

	@PostMapping("addproduct/{retailerId}")
	public String addProduct(@PathVariable long retailerId, @RequestBody ProductForm form) {
		return productService.addProduct(retailerId, form);
	}

	@PutMapping("/updateProduct/{retailerId}/{productId}")
	public String updateProduct(@PathVariable long retailerId, @PathVariable Long productId,
			@RequestBody ProductForm form) {
		return productService.updateProduct(retailerId, productId, form);
	}

	@DeleteMapping("/deleteProduct/{retailerId}/{productId}")
	public String deleteProduct(@PathVariable long retailerId, @PathVariable Long productId) {
		return productService.deleteProduct(retailerId, productId);
	}
	@GetMapping("/getproductbyid/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable Long productId) {
		Product product=productService.findProductById(productId);
		return ResponseEntity.ok(product);
	}
}
