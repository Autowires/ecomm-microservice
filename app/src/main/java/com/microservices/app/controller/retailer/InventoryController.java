package com.microservices.app.controller.retailer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.microservices.app.dto.ProductForm;
import com.microservices.app.service.ProductService;

@Controller
@RequestMapping("/retailer/inventory")
public class InventoryController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public String getAllProducts(Model model, @SessionAttribute Long userId) {
		List<Object> allProducts = productService.getAllProducts(userId);
		System.out.println(allProducts);
		model.addAttribute("products", allProducts);
		return "retailer/inventory";
	}

	@GetMapping("/add-product")
	public String addProduct(Model model) {
		return "retailer/product-add";
	}

	@PostMapping("/add-product")
	public String addProduct(Model model, ProductForm form, @SessionAttribute Long userId) {
		try {
			String mes = productService.addProduct(form, userId);
			return "redirect:/retailer/inventory";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "retailer/product-add";
		}
	}

	@GetMapping("/{id}/updateProduct")
	public String updateProduct(Model model, @PathVariable long id) {
		model.addAttribute("product", productService.getRetailerproduct(id));
		return "retailer/product-update";
	}

	@PostMapping("/{id}/updateProduct")
	public String updateProduct(ProductForm form, Model model, @PathVariable long id, @SessionAttribute Long userId) {
		try {
			String message = productService.updateProduct(userId, id, form);
			return "redirect:/retailer/inventory";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "retailer/product-update";
		}
	}

	@DeleteMapping("/deleteProduct")
	public String deleteProduct(Model model, @RequestParam long productId, @SessionAttribute Long userId) {
		System.out.println("inside the client");
		String message = productService.deleteProduct(userId, productId);
		model.addAttribute("message", message);
		return "redirect:/retailer/inventory";
	}

}
