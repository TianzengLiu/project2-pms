package com.champions.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.champions.exceptions.UnauthorizedException;
import com.champions.exceptions.userIdNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {userIdNotFoundException.class})
	public ResponseEntity<Object> userIdNotFound (userIdNotFoundException e) {
		System.out.println(e);
		
		return new ResponseEntity<Object> (e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {UnauthorizedException.class})
	public ResponseEntity<Object> unauthorizedUser(UnauthorizedException e) {
		System.out.println(e);
		
    	return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {Throwable.class}) 
	public ResponseEntity<Object> catchAll(Throwable e) {
		System.out.println(e);
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
