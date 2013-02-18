package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotConductorDao;
import com.munichosica.myapp.jdbc.MotConductorDaoImpl;

public class MotConductorDaoFactory {

	public static MotConductorDao create(){
		return new MotConductorDaoImpl();
	}
}
