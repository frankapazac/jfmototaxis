package com.munichosica.myapp.exceptions;

public class MotEmpresa extends DaoException{

	public MotEmpresa(String message) {
		super(message);
	}
	
	public MotEmpresa(String message, Throwable cause) {
		super(message,cause);
	}
	
}
