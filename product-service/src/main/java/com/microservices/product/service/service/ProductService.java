package com.microservices.product.service.service;

import java.util.List;

import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.entity.Product;

public interface ProductService {

	List<Product> getProducts(String category, String search);

	String addProduct(long retailerId, ProductForm form);

	String updateProduct(long retailerId, long productId, ProductForm form);

	String deleteProduct(long retailerId, long productId);

	Product findProductById(Long productId);

}
