package com.microservices.wishlist.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(long id) {
        super("Customer not found with id: "+id);
    }
}
