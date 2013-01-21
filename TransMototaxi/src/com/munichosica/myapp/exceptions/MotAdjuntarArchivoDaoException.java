package com.munichosica.myapp.exceptions;

public class MotAdjuntarArchivoDaoException extends DaoException{
	public MotAdjuntarArchivoDaoException(String message) {
		super(message);
	}
	public MotAdjuntarArchivoDaoException(String message,Throwable throwable) {
		super(message, throwable);
	}
}
