package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotZonaDao;
import com.munichosica.myapp.jdbc.MotZonaDaoImpl;

public class MotZonaDaoFactory {
	
	public static MotZonaDao create(){
		
		return new MotZonaDaoImpl();

	}
}
