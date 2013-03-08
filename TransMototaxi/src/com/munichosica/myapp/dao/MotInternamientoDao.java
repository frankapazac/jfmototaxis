package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.exceptions.MotInternamientoDaoException;

public interface MotInternamientoDao {
	public void procesar(MotInternamiento internamiento)throws MotInternamientoDaoException;
}
