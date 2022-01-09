package com.peer.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PeerCustomException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ConstraintViolationException.class)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		String defaultMessage = fieldError.getDefaultMessage();
		PeerCustomError customError = new PeerCustomError(LocalDateTime.now(), defaultMessage, request.getDescription(false));
		return new ResponseEntity<Object>(customError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PeerException.class)
	protected ResponseEntity<Object> handleCustomException(Exception exception, WebRequest request) {
		PeerCustomError customError = new PeerCustomError(LocalDateTime.now(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customError, HttpStatus.OK);
	}	
}
