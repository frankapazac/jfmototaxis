package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotOperFiscalizadorDao;
import com.munichosica.myapp.jdbc.MotOperFiscalizadorDaoImpl;

public class MotOperFiscalizadorDaoFactory {
	
	public static MotOperFiscalizadorDao create(){
		return new MotOperFiscalizadorDaoImpl();
	}
}
