package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.exceptions.MotUnidConductorDaoException;

public interface MotUnidConductorDao {

	public void insert(MotUnidConductor dto) throws MotUnidConductorDaoException; 
	public List<MotUnidConductor>findByCriterio(String criterio,String texto, Long empcodigo_D, Long condcodigo)throws MotUnidConductorDaoException;
}
