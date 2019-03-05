package com.cvc.broker.api.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldsValidationException extends RuntimeException  {

	private static final long serialVersionUID = 880380607103175672L;

	private BindingResult result;

	public FieldsValidationException(String message, BindingResult result) {
		super(message);
		this.setResult(result);
	}
	
}
