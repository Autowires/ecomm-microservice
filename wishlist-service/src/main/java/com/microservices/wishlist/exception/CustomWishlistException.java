package com.microservices.wishlist.exception;

/**
 * Custom exception class for handling wishlist-related exceptions.
 */
public class CustomWishlistException extends RuntimeException {
    public CustomWishlistException(String message, Throwable cause) {
        super(message, cause);
    }

    private static final long serialVersionUID = 1L;
}
