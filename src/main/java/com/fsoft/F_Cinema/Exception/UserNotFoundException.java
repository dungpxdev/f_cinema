package com.fsoft.F_Cinema.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6928722102174494962L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
