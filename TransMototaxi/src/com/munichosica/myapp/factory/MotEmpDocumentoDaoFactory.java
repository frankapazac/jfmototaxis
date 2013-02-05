package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotEmpDocumentoDao;
import com.munichosica.myapp.jdbc.MotEmpDocumentoDaoImpl;

public class MotEmpDocumentoDaoFactory {

	public static MotEmpDocumentoDao create(){
			
		return new MotEmpDocumentoDaoImpl();
	}
	
}
