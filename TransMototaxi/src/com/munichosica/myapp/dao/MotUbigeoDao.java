package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotUbigeoDaoException;

public interface MotUbigeoDao {
	public List<MotUbigeo> findAllDepartamentos() throws MotUbigeoDaoException;
	public List<MotUbigeo> findProvinciaByPrimaryKey(String idubigeo) throws MotUbigeoDaoException;
	public List<MotUbigeo> findDistritoByPrimaryKey(String idubigeo) throws MotUbigeoDaoException;
	public List<MotUbigeo> findByPkValue(String idubigeo,Long value)throws MotUbigeoDaoException;
}
