package com.munichosica.myapp.exceptions;

public class MotMarcaDaoException extends DaoException{

	public MotMarcaDaoException(String message) {
		super(message);
	}

	public MotMarcaDaoException(String message,Throwable throwable) {
		super(message, throwable);
	}
}
