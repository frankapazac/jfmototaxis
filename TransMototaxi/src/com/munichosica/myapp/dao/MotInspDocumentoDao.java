package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInspDocumento;
import com.munichosica.myapp.exceptions.MotInspDocumentoDaoException;

public interface MotInspDocumentoDao {
	public void insert(MotInspDocumento dto) throws MotInspDocumentoDaoException;
	public List<MotInspDocumento> findByIdInspector(int codigo)throws MotInspDocumentoDaoException;
}
