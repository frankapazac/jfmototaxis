package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotAdjuntarArchivoDao;
import com.munichosica.myapp.jdbc.MotAdjuntarArchivoDaoImpl;

public class MotAdjuntarArchivoDaoFactory {
	public static MotAdjuntarArchivoDao create(){
		return new MotAdjuntarArchivoDaoImpl();
	}
}
