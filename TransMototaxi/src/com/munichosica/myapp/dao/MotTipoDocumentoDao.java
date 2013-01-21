package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;

public interface MotTipoDocumentoDao {
	public List<MotTipoDocumento> findByTable(String table) throws MotTipoDocumentoDaoException;
}
