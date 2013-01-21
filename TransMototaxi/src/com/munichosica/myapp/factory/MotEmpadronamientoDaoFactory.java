package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmpadronamientoDao;
import com.munichosica.myapp.jdbc.MotEmpadronamientoDaoImpl;

public class MotEmpadronamientoDaoFactory {
	
	public static MotEmpadronamientoDao create(){
		return new MotEmpadronamientoDaoImpl();
	}
}
