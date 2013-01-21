package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotAsocDocumentoDao;
import com.munichosica.myapp.jdbc.MotAsocDocumentoDaoImpl;

public class MotAsocDocumentoDaoFactory {
	public static MotAsocDocumentoDao create(){
		return new MotAsocDocumentoDaoImpl();
	}
}
