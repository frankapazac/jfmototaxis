package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInternamientoDao;
import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.jdbc.MotInternamientoDaoImpl;

public class MotInternamientoDaoFactory {
	public static MotInternamientoDao create(){
		return new MotInternamientoDaoImpl();
	}
}
