package com.munichosica.myapp.exceptions;

public class MotZonaDaoException extends DaoException{

	public MotZonaDaoException(String message) {
		super (message);
	}
	
	public MotZonaDaoException(String message, Throwable cause) {
		super (message,cause);
	}

	
}
