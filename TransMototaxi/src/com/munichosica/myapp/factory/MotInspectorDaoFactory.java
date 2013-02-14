package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInspectorDao;
import com.munichosica.myapp.jdbc.MotInspectorDaoImpl;

public class MotInspectorDaoFactory {
	public static MotInspectorDao create(){
		return new MotInspectorDaoImpl();
	}
}
