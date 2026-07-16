package com.elearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> userExist(UserAlreadyExistException ex) {

		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

	}

	@ExceptionHandler(CourseAlreadyExistException.class)
	public ResponseEntity<String> courseExist(CourseAlreadyExistException ex) {

		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> Notfound(ResourceNotFoundException rnfe){
		return ResponseEntity.status(HttpStatus.OK).body(rnfe.getMessage());
	}

}