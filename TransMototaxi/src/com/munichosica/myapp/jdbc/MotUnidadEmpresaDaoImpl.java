package com.munichosica.myapp.jdbc;

import java.util.List;

import com.munichosica.myapp.dao.MotUnidadEmpresaDao;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;

public class MotUnidadEmpresaDaoImpl implements MotUnidadEmpresaDao {

	@Override
	public void insert(MotUnidadEmpresa dto)
			throws MotUnidadEmpresaDaoException {		
	}

	@Override
	public void update(MotUnidadEmpresa dto)
			throws MotUnidadEmpresaDaoException {	
	}

	@Override
	public void delete(MotUnidadEmpresa dto)
			throws MotUnidadEmpresaDaoException {
	}

	@Override
	public MotUnidadEmpresa findByPrimaryKey(Long codigo)
			throws MotUnidadEmpresaDaoException {
		return null;
	}

	@Override
	public List<MotUnidadEmpresa> findByCriterio(String criterio, String texto,
			Long modcodigo_D) throws MotUnidadEmpresaDaoException {
		return null;
	}



}
