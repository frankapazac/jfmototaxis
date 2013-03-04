package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.ReportsDao;
import com.munichosica.myapp.jdbc.ReportsDaoImpl;

public class ReportsDaoFactory {
	public static ReportsDao create(){
		return new ReportsDaoImpl();
	}
}
