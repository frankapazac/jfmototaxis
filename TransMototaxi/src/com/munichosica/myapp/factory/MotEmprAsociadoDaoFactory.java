package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmprAsociadoDao;
import com.munichosica.myapp.jdbc.MotEmprAsociadoDaoImpl;

public class MotEmprAsociadoDaoFactory {
	public static MotEmprAsociadoDao create(){
		return new MotEmprAsociadoDaoImpl();
	}
}
