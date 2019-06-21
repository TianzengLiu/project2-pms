package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApiException {

	private static final long serialVersionUID = 1L;

	public static HttpStatus status = HttpStatus.NOT_FOUND;
	
	public NotFoundException(String message) {
		super(status, message);
	}
}
