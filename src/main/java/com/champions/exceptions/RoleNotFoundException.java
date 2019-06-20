package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends AbstractApiException {

	public RoleNotFoundException(HttpStatus hs) {
		super(hs);
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException(HttpStatus hs, String message) {
		super(hs, message);
		// TODO Auto-generated constructor stub
	}
	

}
