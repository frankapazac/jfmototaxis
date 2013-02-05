package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotOficinaRegistralDao;
import com.munichosica.myapp.jdbc.MotOficinaRegistralDaoImpl;

public class MotOficinaRegistralDaoFactory {
	public static MotOficinaRegistralDao create(){
		return new MotOficinaRegistralDaoImpl();
	}
}
