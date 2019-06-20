package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public class PermitNotFoundException extends AbstractApiException {

	public PermitNotFoundException(HttpStatus hs) {
		super(hs);
		// TODO Auto-generated constructor stub
	}
	
	public PermitNotFoundException(HttpStatus hs, String message) {
		super(hs, message);
		// TODO Auto-generated constructor stub
	}
	

}
