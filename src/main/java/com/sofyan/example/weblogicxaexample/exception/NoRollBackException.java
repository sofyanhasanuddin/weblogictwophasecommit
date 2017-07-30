package com.sofyan.example.weblogicxaexample.exception;

public class NoRollBackException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoRollBackException() {
		super();
	}
	
	public NoRollBackException(String msg) {
		super(msg);
	}

}
