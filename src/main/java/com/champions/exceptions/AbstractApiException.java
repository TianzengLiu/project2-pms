package com.champions.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AbstractApiException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	HttpStatus status;
	String message;
	
	public AbstractApiException(HttpStatus hs) {
		
		this.status = hs;
	}
	
	public AbstractApiException(HttpStatus hs, String message) {
		
		this.status = hs;
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
	public String getMessage() {
		return this.message;
	}
	

}
