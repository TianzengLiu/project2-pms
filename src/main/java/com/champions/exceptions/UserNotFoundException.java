package com.champions.exceptions;

public class UserNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String ms) {
		
		super(ms);
	}
}
