package com.microservices.product.service.exception;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException() {
		super("Product not found");
	}

}
