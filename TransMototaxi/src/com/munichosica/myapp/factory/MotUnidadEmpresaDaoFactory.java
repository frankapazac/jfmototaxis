package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotUnidadEmpresaDao;
import com.munichosica.myapp.jdbc.MotUnidadEmpresaDaoImpl;

public class MotUnidadEmpresaDaoFactory {
	public static MotUnidadEmpresaDao create(){
		return new MotUnidadEmpresaDaoImpl();
	}
}
