package com.microservices.orders.service;

import com.microservices.orders.entity.Product;

public interface ProductService {

	Product findProductById(Long productId);

}
