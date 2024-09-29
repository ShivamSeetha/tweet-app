package com.tweetapp.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tweetapp.api.model.ExceptionDetails;
import com.tweetapp.api.model.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * To Handle TweetDoesNotExistsException
	 * 
	 * @param ex
	 * @param request
	 * @return TweetDoesNotExistsException
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(TweetDoesNotExistsException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptions(TweetDoesNotExistsException ex, WebRequest request) {

		final ExceptionDetails errorDetails = new ExceptionDetails(ex.getLocalizedMessage(),
				"Tweet doesn't exists... Kindly try with another id.", request.getDescription(false), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

	}

	/**
	 * To Handle Exception
	 * 
	 * @param ex
	 * @param req
	 * @return Exception
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(req.getDescription(false));
		exceptionResponse.setDateTime(new Date());
		log.error("Exception Message: {}", ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * To Handle UserDoesNotExistsException
	 * 
	 * @param ex
	 * @param request
	 * @return UserDoesNotExistsException
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UserDoesNotExistsException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptions(UserDoesNotExistsException ex, WebRequest request) {

		final ExceptionDetails errorDetails = new ExceptionDetails(ex.getLocalizedMessage(),
				"User doesn't exists... Kindly try with another id.", request.getDescription(false), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

	}

	/**
	 * To Handle UserAlreadyExistsException
	 * 
	 * @param ex
	 * @param request
	 * @return UserAlreadyExistsException
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptions(UserAlreadyExistsException ex, WebRequest request) {

		final ExceptionDetails errorDetails = new ExceptionDetails(ex.getLocalizedMessage(),
				"User already exists... Kindly try with another id.", request.getDescription(false), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

	}
}
