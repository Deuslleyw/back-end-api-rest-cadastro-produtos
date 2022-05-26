package com.deusley.novoProjeto.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;


@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundExcepition.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExcepition er, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),er.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
}
}