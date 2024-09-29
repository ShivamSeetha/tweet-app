package com.tweetapp.api.exception;

public class TweetDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TweetDoesNotExistsException(String msg) {
		super(msg);
	}

}