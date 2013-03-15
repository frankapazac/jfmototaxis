package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;

public interface MotTipoDocumentoDao {
	public List<MotTipoDocumento> findByTable(String table) throws MotTipoDocumentoDaoException;
	public List<MotTipoDocumento> findByCriterio(String criterio,String texto) throws MotTipoDocumentoDaoException;
	public MotTipoDocumento findByIdTipoDoc(Long codigo)throws MotOperativoDaoException;
	public void insert(MotTipoDocumento dto) throws MotTipoDocumentoDaoException;
	public void delete(Integer codigo) throws MotTipoDocumentoDaoException;
}
