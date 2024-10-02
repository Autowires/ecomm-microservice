package com.microservices.app.controller.retailer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.app.dto.ProductForm;
import com.microservices.app.service.ProductService;

@Controller
@RequestMapping("/retailer/inventory")
public class InventoryController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public String getAllProducts(Model model) {
		List<Object> allProducts = productService.getAllProducts((long) 2);
		System.out.println(allProducts);
		model.addAttribute("products", allProducts);
		return "retailer/inventory";
	}

	@GetMapping("/add-product")
	public String addProduct(Model model) {
		return "retailer/product-add";
	}

	@PostMapping("/add-product")
	public String addProduct(Model model, ProductForm form) {
		try {
			String mes = productService.addProduct(form, 2);
			return "redirect:/retailer/inventory";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "retailer/product-add";
		}
	}

	@PutMapping("/updateProduct")
	public String updateProduct(@RequestBody ProductForm form, Model model) {
		String message = productService.updateProduct((long) 1, (long) 4, form);
		model.addAttribute("message", message);
		return "redirect:/retailer/inventory";
	}

	@DeleteMapping("/deleteProduct")
	public String deleteProduct(Model model, @RequestParam long productId) {
		System.out.println("inside the client");
		String message = productService.deleteProduct((long) 2, (long) productId);
		model.addAttribute("message", message);
		return "redirect:/retailer/inventory";
	}

}
