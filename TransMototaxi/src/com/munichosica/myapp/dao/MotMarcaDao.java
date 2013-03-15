package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.exceptions.MotMarcaDaoException;

public interface MotMarcaDao {
	public List<MotMarca> findAll() throws MotMarcaDaoException;
	public List<MotMarca> findByCriterio(String criterio, String texto)throws MotMarcaDaoException;
	public MotMarca findByIdMarca(Long codigo) throws MotMarcaDaoException;
	public void insert(MotMarca dto) throws MotMarcaDaoException;
	public void delete(Integer codigo) throws MotMarcaDaoException;
}
