package com.restapp.ccprocess.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidCardException.class)
	public final ResponseEntity<Object> handleInvalidCardException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse =	new ExceptionResponse(new Date(), "Invalid card data",ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingFieldsException.class)
	public final ResponseEntity<Object> handleMissingFieldsException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse =	new ExceptionResponse(new Date(), "Missing mandatory fields",ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
