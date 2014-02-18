package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.exceptions.MotUnidDocumentoDaoException;

public interface MotUnidDocumentoDao {
	public void insert(MotUnidDocumento dto)throws MotUnidDocumentoDaoException;
	public List<MotUnidDocumento> findDocumentosByIdUnidad(Long codigo)throws MotUnidDocumentoDaoException;
	public List<MotUnidDocumento> findFotosByIdUnidad(Long codigo)throws MotUnidDocumentoDaoException;
	public List<MotUnidDocumento> findMensajesPlaca(String placa)throws MotUnidDocumentoDaoException;
}
