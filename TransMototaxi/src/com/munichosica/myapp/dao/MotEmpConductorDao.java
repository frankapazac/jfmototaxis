package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotEmpConductor;
import com.munichosica.myapp.exceptions.MotEmpConductorDaoException;

public interface MotEmpConductorDao {
	
	public void procesar(MotEmpConductor dto) throws MotEmpConductorDaoException;
	public void update(MotEmpConductor dto) throws MotEmpConductorDaoException;
	public void delete(MotEmpConductor dto) throws MotEmpConductorDaoException;
	public MotEmpConductor findByPrimaryKey(Long codigo) throws MotEmpConductorDaoException;
	public List<MotEmpConductor> findByCriterio (String criterio,String texto, Long concodigoD)throws MotEmpConductorDaoException ;
	
}
