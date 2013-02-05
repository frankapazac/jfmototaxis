package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmpresaDao;
import com.munichosica.myapp.dao.MotParaderoDao;
import com.munichosica.myapp.jdbc.MotEmpresaDaoImpl;

public class MotEmpresaDaoFactory {

	public static MotEmpresaDao create(){
			
		return new MotEmpresaDaoImpl();
		
	}
}
