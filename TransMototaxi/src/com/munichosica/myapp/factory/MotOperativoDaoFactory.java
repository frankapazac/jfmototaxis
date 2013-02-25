package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotOperativoDao;
import com.munichosica.myapp.jdbc.MotOperativoDaoImpl;

public class MotOperativoDaoFactory {
	public static MotOperativoDao create(){
		return new MotOperativoDaoImpl();
	}
}
