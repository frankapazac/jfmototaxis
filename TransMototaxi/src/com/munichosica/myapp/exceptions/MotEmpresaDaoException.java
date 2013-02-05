package com.munichosica.myapp.exceptions;

public class MotEmpresaDaoException extends DaoException{

	public MotEmpresaDaoException(String message) {
		super(message);
	}
	
	public MotEmpresaDaoException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
