package com.deusley.novoProjeto.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.deusley.novoProjeto.services.Exceptions.AuthorizationException;
import com.deusley.novoProjeto.services.Exceptions.DataIntegrityExcepition;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;


@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundExcepition.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExcepition er, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),er.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
}
	
	
	@ExceptionHandler(DataIntegrityExcepition.class)
	public ResponseEntity<StandardError> IntegrityData(DataIntegrityExcepition er, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),er.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	
}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> Validation(MethodArgumentNotValidException er, HttpServletRequest request){
		
			ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de Validação",System.currentTimeMillis());
	
		for(FieldError x : er.getBindingResult().getFieldErrors()){
			err.addError(x.getField(), x.getDefaultMessage());
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	
}
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> Authorization(AuthorizationException er, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(),er.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	
}}