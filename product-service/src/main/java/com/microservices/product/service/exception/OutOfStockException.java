package com.microservices.product.service.exception;

public class OutOfStockException extends Exception {
	public OutOfStockException(Long productId) {
		super("out of the stock for the product "+productId);
	}

}
