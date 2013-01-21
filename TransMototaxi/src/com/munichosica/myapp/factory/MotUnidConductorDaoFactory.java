package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotUnidConductorDao;
import com.munichosica.myapp.jdbc.MotUnidConductorDaoImpl;

public class MotUnidConductorDaoFactory {

	public static MotUnidConductorDao create(){
		return new MotUnidConductorDaoImpl();
	}
	
}
