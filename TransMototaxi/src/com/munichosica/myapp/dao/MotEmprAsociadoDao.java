package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;

public interface MotEmprAsociadoDao {
	public void procesar(MotEmprAsociado dto) throws MotEmprAsociadoDaoException;
	public void delete(Long codigo) throws MotEmprAsociadoDaoException;
	public MotEmprAsociado findByPrimaryKey(Long codigo) throws MotEmprAsociadoDaoException;
	public List<MotEmprAsociado> findByCriterio(String criterio, String texto, Long empcodigoD) throws MotEmprAsociadoDaoException;
}
