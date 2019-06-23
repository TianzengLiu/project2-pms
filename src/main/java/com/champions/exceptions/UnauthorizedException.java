package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AbstractApiException {

	public static HttpStatus status = HttpStatus.FORBIDDEN;
	
	public static String message = "You are forbidden to access!";
	
	public UnauthorizedException() {
		super(status, message);
	}

}
