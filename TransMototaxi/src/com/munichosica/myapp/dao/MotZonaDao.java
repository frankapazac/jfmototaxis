package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotZonaDaoException;

public interface MotZonaDao {

	public void insert(MotZona dto) throws MotZonaDaoException;
	public MotZona findByPrimaryKey (Long codigo) throws MotZonaDaoException;
	public List<MotZona> findAll() throws MotZonaDaoException;
	public List<MotZona> findByCriterio(String criterio, String texto)throws MotZonaDaoException;
	public void delete(Long codigo) throws MotZonaDaoException;


}
