package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;

public interface MotAdjuntarArchivoDao {
	public void insert(MotAdjuntarArchivo adjuntarArchivo)throws MotAdjuntarArchivoDaoException;
	public List<MotAdjuntarArchivo> findArchivosByAsociado(Long codigo) throws MotAdjuntarArchivoDaoException;
	
}
