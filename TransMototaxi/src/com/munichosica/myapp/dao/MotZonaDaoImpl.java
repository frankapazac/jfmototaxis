package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotZonaDaoException;

public interface MotZonaDaoImpl {
	public List<MotZona> findAll ()throws MotZonaDaoException ;
}
