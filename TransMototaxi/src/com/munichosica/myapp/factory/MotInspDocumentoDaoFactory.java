package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInspDocumentoDao;
import com.munichosica.myapp.jdbc.MotInspDocumentoDaoImpl;

public class MotInspDocumentoDaoFactory {
	public static MotInspDocumentoDao create(){
		return new MotInspDocumentoDaoImpl();
	}
}
