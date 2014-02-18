package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotAuditoriaDao;
import com.munichosica.myapp.jdbc.MotAuditoriaDaoImpl;

public class MotAuditoriaDaoFactory {
	public static MotAuditoriaDao create(){
		return new MotAuditoriaDaoImpl();
	}
}
