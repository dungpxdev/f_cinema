package com.fsoft.F_Cinema.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle any exception
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String messages = ex.getLocalizedMessage();
		if (messages == null)
			messages = ex.toString();
		MessageError apiError = new MessageError(new Date(), messages, null);

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle NullpointerException
	 */
	@ExceptionHandler(value = { NullPointerException.class, UserNotFoundException.class })
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {
		String messages = ex.getLocalizedMessage();
		if (messages == null)
			messages = ex.toString();
		MessageError apiError = new MessageError(new Date(), messages, null);

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

