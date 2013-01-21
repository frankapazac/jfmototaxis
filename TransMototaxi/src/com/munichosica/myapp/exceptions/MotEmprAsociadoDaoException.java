package com.munichosica.myapp.exceptions;

public class MotEmprAsociadoDaoException extends DaoException{

	public MotEmprAsociadoDaoException(String message) {
		super(message);
	}
	
	public MotEmprAsociadoDaoException(String message,Throwable cause){
		super(message, cause);
	}
}
