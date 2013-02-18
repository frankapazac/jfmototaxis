package com.munichosica.myapp.factory;
import com.munichosica.myapp.dao.MotCondDocumentoDao;


import com.munichosica.myapp.jdbc.MotCondDocumentoDaoImpl;

public class MotCondDocumentoDaoFactory {
		
	public static MotCondDocumentoDao create(){
		return new MotCondDocumentoDaoImpl();
	}
	
}
