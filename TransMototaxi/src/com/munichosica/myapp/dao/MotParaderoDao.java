package com.munichosica.myapp.dao;

import java.util.List;


import com.munichosica.myapp.dto.MotEmpParadero;
import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;

public interface MotParaderoDao {

	public void insert(MotParadero dto) throws MotParaderoDaoException;
	public void update(MotParadero dto) throws MotParaderoDaoException;
	public void delete(MotParadero dto) throws MotParaderoDaoException;
	public MotParadero findByPrimaryKey(Long codigo) throws MotParaderoDaoException;
	public List<MotParadero> findByCriterio(String criterio,String texto, Long parcodigoI) throws MotParaderoDaoException; 
	public List<MotParadero> findZonaByEmpresa(Long empcodigoD) throws MotParaderoDaoException;
	public MotParadero findByParadero(int codigo) throws MotParaderoDaoException;

}
