package com.cg.fms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FeedbackNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FeedbackNotFoundException(String message) {
		super(message);	
	}
	
}