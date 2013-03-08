package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;

public interface MotInspectorDao {
	public void procesar(MotInspector dto) throws MotInspectorDaoException;
	public List<MotInspector> findByCriterio(String criterio,String texto) throws MotInspectorDaoException;
	public MotInspector findByPrimaryKey(int codigo) throws MotInspectorDaoException;
	public void delete(int codigo) throws MotInspectorDaoException;
	public List<MotInspector> findAll() throws MotInspectorDaoException;
	
}
