package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotPapeletaDao;
import com.munichosica.myapp.jdbc.MotPapeletaDaoImpl;

public class MotPapeletaDaoFactory {
	public static MotPapeletaDao create(){
		return new MotPapeletaDaoImpl();
	}
}
