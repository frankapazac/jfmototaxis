package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotPoliciaDao;
import com.munichosica.myapp.jdbc.MotPoliciaDaoImpl;

public class MotPoliciaDaoFactory {
	public static MotPoliciaDao create(){
		return new MotPoliciaDaoImpl();
	}
}
