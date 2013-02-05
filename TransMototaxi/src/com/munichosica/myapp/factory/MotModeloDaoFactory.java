package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotModeloDao;
import com.munichosica.myapp.jdbc.MotModeloDaoImpl;

public class MotModeloDaoFactory {
	public static MotModeloDao create(){
		return new MotModeloDaoImpl();
	}
}
