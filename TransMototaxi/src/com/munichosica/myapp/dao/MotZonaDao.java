package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotZonaDaoException;

public interface MotZonaDao {

	public void insert(MotZonaDao dto) throws MotZonaDaoException;
	public void update(MotZonaDao dto) throws MotZonaDaoException;
	public void delete(MotZonaDao dto) throws MotZonaDaoException;
	public MotZonaDao findByPrimaryKey (Long codigo) throws MotZonaDaoException;
	public List<MotZona> findAll() throws MotZonaDaoException;

}
