package com.champions.exceptions;

import org.springframework.http.HttpStatus;


public class UnauthorizedException extends AbstractApiException {

	public UnauthorizedException(HttpStatus hs) {
		super(hs);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedException(HttpStatus hs, String message) {
		super(hs, message);
		// TODO Auto-generated constructor stub
	}

}
