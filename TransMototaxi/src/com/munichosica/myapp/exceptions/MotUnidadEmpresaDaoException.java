package com.munichosica.myapp.exceptions;

public class MotUnidadEmpresaDaoException  extends DaoException{

	public MotUnidadEmpresaDaoException(String message) {
		super(message);
	}
	
	public MotUnidadEmpresaDaoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
