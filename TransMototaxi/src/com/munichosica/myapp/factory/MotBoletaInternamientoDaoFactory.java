package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotBoletaInternamientoDao;
import com.munichosica.myapp.jdbc.MotBoletaInternamientoDaoImpl;

public class MotBoletaInternamientoDaoFactory {
	public static MotBoletaInternamientoDao create(){
		return new MotBoletaInternamientoDaoImpl();
	}
}
