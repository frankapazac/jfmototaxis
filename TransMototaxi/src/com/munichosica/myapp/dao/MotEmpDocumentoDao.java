package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;

public interface MotEmpDocumentoDao {

	public void insert(MotEmpDocumento dto) throws MotEmpDocumentoDaoException;
	
	
}
