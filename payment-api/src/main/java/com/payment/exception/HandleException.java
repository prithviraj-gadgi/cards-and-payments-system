package com.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HandleException {
	
	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex,
			WebRequest request) {
		
		return new ResponseEntity<>("Invalid data, Payment failed!!!", HttpStatus.CONFLICT);
	}
	
	
}
