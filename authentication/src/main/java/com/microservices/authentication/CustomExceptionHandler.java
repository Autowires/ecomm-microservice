package com.microservices.authentication;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.microservices.authentication.exception.UserNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("status", HttpStatus.BAD_REQUEST.value());
		errors.put("message", "Validation failed");

		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		});

		errors.put("errors", fieldErrors);
		return ResponseEntity.badRequest().body(errors);
	}
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
