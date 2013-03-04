package com.munichosica.myapp.dao;

import java.util.List;
import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.exceptions.MotInfraccionDaoException;


public interface MotInfraccionDao {
	
	public List<MotInfraccion> findByCriterio(String criterio,String texto) throws MotInfraccionDaoException;
	public void procesar(MotInfraccion dto) throws MotInfraccionDaoException;
	public void delete(MotInfraccion dto) throws MotInfraccionDaoException;
	public MotInfraccion findByIdInfraccion(Long codigo) throws MotInfraccionDaoException;
	public List<MotInfraccion> findAll() throws MotInfraccionDaoException;
	public List<MotInfraccion> findByEstado(String estado) throws MotInfraccionDaoException;

}
