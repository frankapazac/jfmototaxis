package com.munichosica.myapp.exceptions;

public class MotAsocDocumentoDaoException extends DaoException{
	public MotAsocDocumentoDaoException(String message) {
		super(message);
	}
	
	public MotAsocDocumentoDaoException(String message,Throwable throwable) {
		super(message, throwable);
	}
}
