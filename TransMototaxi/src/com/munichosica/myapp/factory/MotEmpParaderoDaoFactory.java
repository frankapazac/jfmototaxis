package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmpParaderoDao;
import com.munichosica.myapp.jdbc.MotEmpParaderoDaoImpl;

public class MotEmpParaderoDaoFactory {

	public static MotEmpParaderoDao create(){
		return new MotEmpParaderoDaoImpl();
	}
	
}
