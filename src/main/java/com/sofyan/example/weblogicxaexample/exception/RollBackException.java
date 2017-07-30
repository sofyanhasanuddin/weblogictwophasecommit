package com.sofyan.example.weblogicxaexample.exception;

public class RollBackException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RollBackException() {
		super();
	}
	
	public RollBackException(String msg) {
		super(msg);
	}

}
