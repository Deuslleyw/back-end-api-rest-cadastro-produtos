package com.deusley.novoProjeto.services.Exceptions;

public class AuthorizationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
		super(msg);
	}

	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
		
	}

