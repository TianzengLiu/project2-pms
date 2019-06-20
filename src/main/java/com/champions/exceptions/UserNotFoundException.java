package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractApiException {

	public UserNotFoundException(HttpStatus hs) {
		super(hs);
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException(HttpStatus hs, String message) {
		super(hs, message);
		// TODO Auto-generated constructor stub
	}
	

}
