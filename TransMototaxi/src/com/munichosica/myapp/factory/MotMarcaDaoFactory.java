package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotMarcaDao;
import com.munichosica.myapp.jdbc.MotMarcaDaoImpl;

public class MotMarcaDaoFactory {
	public static MotMarcaDao create(){
		return new MotMarcaDaoImpl();
	}
}
