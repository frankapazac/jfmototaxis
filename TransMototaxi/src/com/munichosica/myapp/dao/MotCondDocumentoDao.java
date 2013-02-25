package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;

public interface MotCondDocumentoDao {

	public void insert(MotCondDocumento dto) throws MotCondDocumentoDaoException;
	public void delete(MotCondDocumento dto) throws MotCondDocumentoDaoException;
	public MotCondDocumento findByPrimaryKey(Long codigo) throws MotCondDocumentoDaoException;
	List<MotCondDocumento> findByIdConductor(Long codigo)throws MotCondDocumentoDaoException;
	
}
