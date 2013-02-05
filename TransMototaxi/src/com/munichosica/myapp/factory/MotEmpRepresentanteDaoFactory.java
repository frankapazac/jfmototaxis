package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmpRepresentanteDao;
import com.munichosica.myapp.jdbc.MotEmpRepresentanteDaoImpl;

public class MotEmpRepresentanteDaoFactory  {

	public static MotEmpRepresentanteDao create(){
		return new MotEmpRepresentanteDaoImpl();
	}
	
	
}
