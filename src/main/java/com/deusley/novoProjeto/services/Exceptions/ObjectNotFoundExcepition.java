package com.deusley.novoProjeto.services.Exceptions;

public class ObjectNotFoundExcepition extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExcepition(String msg) {
		super(msg);
	}

	public ObjectNotFoundExcepition(String msg, Throwable cause) {
		super(msg, cause);
	}
		
	}

