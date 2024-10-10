package com.microservices.app.service;

import java.util.List;

import com.microservices.app.dto.Product;
import com.microservices.app.dto.ProductForm;

public interface ProductService {

	List<Product> getProducts(String category, String search);

	List<String> getAllCategories();

	List<Object> recentProducts();

	Object getproduct(int pid);

	List<Object> getAllProducts(Long retailerId);

	String deleteProduct(long retailerId, long productId);

	String updateProduct(long retailerId, long productId, ProductForm form);

	String addProduct(ProductForm form, long retailerId);

	Object getRetailerproduct(long productId);

	List<Object> getRetailerOrders(long id);

	Object getProductReviews(int productId);

}
