package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;

public interface MotAsocDocumentoDao {
	public void insert(MotAsocDocumento asocDocumento)throws MotAsocDocumentoDaoException;

	public List<MotAsocDocumento> findByIdAsociado(Long codigo)throws MotAsocDocumentoDaoException;
}
