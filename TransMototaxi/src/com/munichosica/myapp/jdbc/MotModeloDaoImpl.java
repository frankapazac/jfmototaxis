package com.munichosica.myapp.jdbc;

import java.util.List;

import com.munichosica.myapp.dao.MotModeloDao;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;

public class MotModeloDaoImpl implements MotModeloDao {

	@Override
	public void insert(MotModelo dto) throws MotModeloDaoException {
	}

	@Override
	public void update(MotModelo dto) throws MotModeloDaoException {		
	}

	@Override
	public void delete(MotModelo dto) throws MotModeloDaoException {
	}

	@Override
	public MotModelo findByPrimaryKey(int codigo) throws MotModeloDaoException {
		return null;
	}

	@Override
	public List<MotModelo> findByCriterio(String criterio, String texto,
			Long modcodigo_I) throws MotParaderoDaoException {
		return null;
	}
	
}
