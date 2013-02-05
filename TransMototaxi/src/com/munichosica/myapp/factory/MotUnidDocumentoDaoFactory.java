package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotUnidDocumentoDao;
import com.munichosica.myapp.jdbc.MotUnidDocumentoDaoImpl;

public class MotUnidDocumentoDaoFactory {
	public static MotUnidDocumentoDao create(){
		return new MotUnidDocumentoDaoImpl();
	}
}
