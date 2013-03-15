package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInteInventarioDao;
import com.munichosica.myapp.jdbc.MotInteInventarioDaoImpl;

public class MotInteInventarioDaoFactory {
	public static MotInteInventarioDao create(){
		return new MotInteInventarioDaoImpl();
	}
}
