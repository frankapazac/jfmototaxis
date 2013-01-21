package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;

public interface MotModeloDao {

	public void insert(MotModelo dto) throws MotModeloDaoException;
	public void update(MotModelo dto) throws MotModeloDaoException;
	public void delete(MotModelo dto) throws MotModeloDaoException;
	public MotModelo findByPrimaryKey(int codigo) throws MotModeloDaoException;
	public List<MotModelo> findByCriterio(String criterio,String texto, Long modcodigo_I)throws MotParaderoDaoException;

	
}
