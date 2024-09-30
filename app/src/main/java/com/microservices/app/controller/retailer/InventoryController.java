package com.microservices.app.controller.retailer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.app.dto.ProductForm;
import com.microservices.app.service.ProductService;
@Controller
@RequestMapping("/retailer/inventory")
public class InventoryController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public String  getAllProducts(Model model){
		List<Object> allProducts=productService.getAllProducts((long) 2);
		model.addAttribute("products",allProducts);
		System.out.println(allProducts);
		return "Inventory";
	}
	@PutMapping("/updateProduct")
	public String updateProduct(@RequestBody ProductForm form,Model model) {
		String message=productService.updateProduct((long)1,(long)4, form);
		model.addAttribute("message",message);
		return "Inventory";
	}

	@DeleteMapping("/deleteProduct")
	public String deleteProduct(Model model) {
		System.out.println("inside the client");
		String message= productService.deleteProduct((long)1,(long)1);
		model.addAttribute("message",message);
		return "Inventory";
	}

	
}
