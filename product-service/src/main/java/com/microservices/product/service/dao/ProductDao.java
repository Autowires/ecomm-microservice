package com.microservices.product.service.dao;

import java.util.List;

import com.microservices.product.service.dto.ProductDTO;
import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.dto.ProductInfoResponse;
import com.microservices.product.service.entity.Product;

public interface ProductDao {

	List<Product> getProducts(String category, String search);

	String addProduct(long retailerId, ProductForm form);

	String updateProduct(long retailerId, Long productId, ProductForm form);

	String deleteProduct(long retailerId, Long productId);

	Product findProductById(Long productId);

	List<String> getCategories();

	List<Product> recentAdds();

	ProductInfoResponse getProductInfo(long productId);

	List<Product> getAllProducts(Long retailerId);

}
