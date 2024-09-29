package com.tweetapp.api.exception;

public class UserDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserDoesNotExistsException(String msg) {
		super(msg);
	}

}