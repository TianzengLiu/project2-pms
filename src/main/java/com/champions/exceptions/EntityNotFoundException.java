package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AbstractApiException {

	public EntityNotFoundException(HttpStatus hs) {
		super(hs);
		// TODO Auto-generated constructor stub
	}
	
	public EntityNotFoundException(HttpStatus hs, String message) {
		super(hs, message);
		// TODO Auto-generated constructor stub
	}
	

}
