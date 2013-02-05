package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotPropUnidadEmpresaDao;
import com.munichosica.myapp.jdbc.MotPropUnidadEmpresaDaoImpl;

public class MotPropUnidadEmpresaDaoFactory {
	public static MotPropUnidadEmpresaDao create(){
		return new MotPropUnidadEmpresaDaoImpl();
	}
}
