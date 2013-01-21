package com.munichosica.myapp.exceptions;

public class MotUbigeoDaoException extends DaoException{
	public MotUbigeoDaoException(String message) {
		super(message);
	}
	public MotUbigeoDaoException(String message,Throwable throwable){
		super(message, throwable);
	}
}
