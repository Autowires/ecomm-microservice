package com.microservices.orders.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.microservices.orders.entity.Product;
@Service
public class ProductServiceImplementation implements ProductService {

	@Override
	public Product findProductById(Long productId) {
		Product product=null;
	if (productId==1) {
		 product=new Product();
		product=new Product();
		product.setProductId(1L);
		product.setPrice(new BigDecimal(1000.0));
		product.setStock(6);
		
		
	}
	if (productId==2) {
		product=new Product();
		product.setProductId(2L);
		product.setPrice(new BigDecimal(5000.0));
		product.setStock(3);
		
	    
	}
	return product;
		
	}

}
