package com.elearning.exception;

public class CourseAlreadyExistException extends RuntimeException{

	public CourseAlreadyExistException(String msg) {
		super(msg);
	}
}
