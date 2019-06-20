package com.champions.exceptions;

import org.springframework.http.HttpStatus;



public class userIdNotFoundException extends AbstractApiException {

	public userIdNotFoundException(HttpStatus hs) {
		super(hs);
		// TODO Auto-generated constructor stub
	}
	
	public userIdNotFoundException(HttpStatus hs, String message) {
		super(hs, message);
		// TODO Auto-generated constructor stub
	}
	

}
