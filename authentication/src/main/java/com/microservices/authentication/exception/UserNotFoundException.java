package com.microservices.authentication.exception;

public class UserNotFoundException extends RuntimeException {
    
	public UserNotFoundException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6172177393170335560L;

}
