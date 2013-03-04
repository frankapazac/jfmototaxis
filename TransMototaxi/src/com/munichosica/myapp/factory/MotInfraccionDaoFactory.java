package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInfraccionDao;
import com.munichosica.myapp.jdbc.MotInfraccionDaoImpl;

public class MotInfraccionDaoFactory {
	public static MotInfraccionDao create(){
		return new MotInfraccionDaoImpl();
	}
}
