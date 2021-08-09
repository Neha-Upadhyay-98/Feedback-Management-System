package com.example.springboottestingexample.exception;

public class CustomerRegistrationException extends Exception {

	  public CustomerRegistrationException(String message) {
	        super(message);
	    }

	    public CustomerRegistrationException(String message, Throwable cause) {
	        super(message, cause);
	    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
