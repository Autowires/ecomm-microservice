package com.microservices.customer.exception;

public class AddWishlistItemException extends RuntimeException {
    public AddWishlistItemException(String message) {
        super(message);
    }
}