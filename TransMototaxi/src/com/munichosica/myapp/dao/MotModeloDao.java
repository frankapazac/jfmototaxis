package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;

public interface MotModeloDao {


	public List<MotModelo> findAll() throws MotModeloDaoException;
	public List<MotModelo> findByCriterio(String criterio, String texto)throws MotModeloDaoException;
	public void insert(MotModelo dto) throws MotModeloDaoException;
	public void delete(Integer codigo) throws MotModeloDaoException;
	public MotModelo findByIdModelo(Long codigo) throws MotModeloDaoException;	
	
}
