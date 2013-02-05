package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.exceptions.MotMarcaDaoException;

public interface MotMarcaDao {
	public List<MotMarca> findAll() throws MotMarcaDaoException;
}
