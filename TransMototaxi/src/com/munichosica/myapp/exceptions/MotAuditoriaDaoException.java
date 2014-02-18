package com.munichosica.myapp.exceptions;

public class MotAuditoriaDaoException extends DaoException{

	private static final long serialVersionUID = 1L;

	public MotAuditoriaDaoException(String message) {
		super(message);
	}
	
	public MotAuditoriaDaoException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
