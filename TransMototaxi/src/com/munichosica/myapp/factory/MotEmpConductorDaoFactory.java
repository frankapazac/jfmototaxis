package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmpConductorDao;
import com.munichosica.myapp.jdbc.MotEmpConductorDaoImpl;

public class MotEmpConductorDaoFactory {
	
	public static MotEmpConductorDao create(){
		return new MotEmpConductorDaoImpl();
	}
}
