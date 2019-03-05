package com.cvc.broker.api.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -5893430729377801662L;

	public ValidationException(String message) {
        super(message);
    }
	
}