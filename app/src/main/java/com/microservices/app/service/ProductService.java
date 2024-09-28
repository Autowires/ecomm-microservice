package com.microservices.app.service;

import java.util.List;

import com.microservices.app.dto.Product;

public interface ProductService {

	List<Product> getProducts(String category, String search);

	List<String> getAllCategories();

	List<Object> recentProducts();
	
	Object getproduct(int pid);


}
