package com.cvc.broker.api.rest.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.cvc.broker.api.exception.FieldsValidationException;
import com.cvc.broker.api.exception.RecordNotFound;
import com.cvc.broker.api.exception.ValidationException;
import com.cvc.broker.api.to.ErrorDetails;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
	
	
	@ExceptionHandler({ValidationException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDetails handleNotImplementedException(ValidationException ex, WebRequest request) {
		return ErrorDetails.builder()
				.addDetail("Validation.")
				.addDescription(ex.getMessage())
				.addStatus(HttpStatus.BAD_REQUEST)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request))
				.build();
	}
	
	
	@ExceptionHandler({FieldsValidationException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDetails handleNotImplementedException(FieldsValidationException ex, WebRequest request) {

		List<String> listErros = new ArrayList<String>();
		ex.getResult().getAllErrors().forEach(error -> listErros.add(error.getDefaultMessage()));
		
		return ErrorDetails.builder()
				.addDetail("Validation.")
				.addDescription(ex.getMessage())
				.addErros(listErros)
				.addStatus(HttpStatus.BAD_REQUEST)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request))
				.build();
	}
	
	
	@ExceptionHandler({RecordNotFound.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDetails handleNotImplementedException(RecordNotFound ex, WebRequest request) {
		return ErrorDetails.builder()
				.addDetail("Object not found with the specified id.")
				.addDescription(ex.getMessage())
				.addStatus(HttpStatus.NOT_FOUND)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request))
				.build();
	}
	
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorDetails handleNotImplementedException(Exception ex, WebRequest request) {
		return ErrorDetails.builder()
				.addDetail("An exception was thrown.")
				.addDescription(ex.getMessage())
				.addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request))
				.build();
	}
	
	
	private String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}
	
	
	private String getHttpMethod(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getMethod();
	}
}
