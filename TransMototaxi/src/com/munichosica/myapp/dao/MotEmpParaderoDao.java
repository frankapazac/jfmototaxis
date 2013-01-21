package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotEmpParadero;
import com.munichosica.myapp.exceptions.MotEmpParaderoDaoException;

public interface MotEmpParaderoDao {

	public void insert(MotEmpParadero dto) throws MotEmpParaderoDaoException;
	public void delete(MotEmpParadero dto) throws MotEmpParaderoDaoException;
}
