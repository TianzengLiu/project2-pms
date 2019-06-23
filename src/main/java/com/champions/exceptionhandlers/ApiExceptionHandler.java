package com.champions.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.champions.exceptions.PermitNotFoundException;
import com.champions.exceptions.RoleNotFoundException;
import com.champions.exceptions.UnauthorizedException;
import com.champions.exceptions.UserNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<Object> userNotFound(UserNotFoundException e) {
		
		System.out.println(e);
		return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {PermitNotFoundException.class})
	public ResponseEntity<Object> permitNotFound(PermitNotFoundException e) {
		
		System.out.println(e);
		return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {RoleNotFoundException.class})
	public ResponseEntity<Object> roleNotFound(RoleNotFoundException e) {
		
		System.out.println(e);
		return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {UnauthorizedException.class})
	public ResponseEntity<Object> unauthorized(UnauthorizedException e) {
		
		System.out.println(e);
		return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {Throwable.class}) 
	public ResponseEntity<Object> catchAll(Throwable e) {
		
		System.out.println(e);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
