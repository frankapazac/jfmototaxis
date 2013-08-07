package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.UsuEmpDao;
import com.munichosica.myapp.jdbc.UsuEmpDaoImpl;

public class UsuEmpDaoFactory {
	public static UsuEmpDao create(){
		return new UsuEmpDaoImpl();
	}
}
