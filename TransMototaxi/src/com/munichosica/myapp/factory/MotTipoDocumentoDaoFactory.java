package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotTipoDocumentoDao;
import com.munichosica.myapp.jdbc.MotTipoDocumentoDaoImpl;

public class MotTipoDocumentoDaoFactory {
	public static MotTipoDocumentoDao create(){
		return new MotTipoDocumentoDaoImpl();
	}
}
