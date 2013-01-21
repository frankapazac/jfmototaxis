package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.exceptions.MotConductorDaoException;

public interface MotConductorDao {
	
	public void insert(MotConductor dto) throws MotConductorDaoException;
	public void update(MotConductor dto) throws MotConductorDaoException;
	public void delete(MotConductor dto) throws MotConductorDaoException;
	public MotConductor findByPrimaryKey(Long codigo) throws MotConductorDaoException;
	public List<MotConductor> findByCriterio (String criterio,String texto, Long concodigoD)throws MotConductorDaoException ;
	
}
