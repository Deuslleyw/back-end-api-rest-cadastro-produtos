package com.deusley.novoProjeto.services.Exceptions;

public class DataIntegrityExcepition extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExcepition(String msg) {
		super(msg);
	}

	public DataIntegrityExcepition(String msg, Throwable cause) {
		super(msg, cause);
	}
		
	}

