package com.cg.fms.exception;

public class CourseAdditionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseAdditionException(String message) {
		super(message);
	}

	public CourseAdditionException(String message, Throwable cause) {
		super(message, cause);
	}
		
}
