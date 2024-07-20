package com.cards.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.NoSuchElementException;

import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex,
			WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		errorMessage.setMessage("Card already exists!");
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex,
			WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		errorMessage.setMessage("Card does not exists!");
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = { HibernateException.class })
	public ResponseEntity<Object> handleHibernateException(HibernateException ex,
			WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		errorMessage.setMessage("You can not change card number!");
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex,
			WebRequest request) {
		
		return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {ResourceAlreadyExistsException.class})
	public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
		String message = ex.getMessage();
		return new ResponseEntity<Object>(message, HttpStatus.CONFLICT);
	}
	
	
}
