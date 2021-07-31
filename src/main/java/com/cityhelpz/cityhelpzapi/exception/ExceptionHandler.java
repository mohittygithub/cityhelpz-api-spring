package com.cityhelpz.cityhelpzapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {
	
	// handle specific exceptions
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(IncompleteFormDataException.class)
	public ResponseEntity<?> handleIncompleteFormDataException(IncompleteFormDataException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	// handle global exceptions
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
