package com.microservices.product.service.exception;

public class OrderProcessingException extends RuntimeException {
  public OrderProcessingException(String message) {
	  super(message);
	  
  }
}
