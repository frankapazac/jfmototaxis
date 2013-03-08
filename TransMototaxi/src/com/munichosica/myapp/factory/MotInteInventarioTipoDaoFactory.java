package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInteInventarioTipoDao;
import com.munichosica.myapp.jdbc.MotInteInventarioTipoDaoImpl;

public class MotInteInventarioTipoDaoFactory {
	public static MotInteInventarioTipoDao create(){
		return new MotInteInventarioTipoDaoImpl();
	}
}
