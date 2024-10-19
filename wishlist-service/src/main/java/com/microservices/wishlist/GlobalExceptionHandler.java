package com.microservices.wishlist;

import com.microservices.wishlist.exception.CustomWishlistException;
import com.microservices.wishlist.exception.CustomerNotFoundException;
import com.microservices.wishlist.exception.ProductNotFoundException;
import com.microservices.wishlist.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class, CustomerNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CustomWishlistException.class)
    public ResponseEntity<String> handleWishlistException(CustomWishlistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
