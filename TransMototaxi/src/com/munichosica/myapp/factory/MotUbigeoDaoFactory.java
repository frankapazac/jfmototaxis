package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotUbigeoDao;
import com.munichosica.myapp.jdbc.MotUbigeoDaoImpl;

public class MotUbigeoDaoFactory {
	public static MotUbigeoDao create(){
		return new MotUbigeoDaoImpl();
	}
}
