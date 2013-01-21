package com.munichosica.myapp.factory;
import com.munichosica.myapp.dao.MotParaderoDao;
import com.munichosica.myapp.jdbc.MotParaderoDaoImpl;

public class MotParaderoDaoFactory {

	public static MotParaderoDao create(){
		return new MotParaderoDaoImpl();
	}
	
}
