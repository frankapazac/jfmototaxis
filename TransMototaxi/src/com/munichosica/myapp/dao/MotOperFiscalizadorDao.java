package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;

public interface MotOperFiscalizadorDao {
	
	public List<MotOperFiscalizador> findByCriterio(String criterio,String texto) throws MotOperFiscalizadorDaoException;
	public List<MotOperFiscalizador> findByIdOperativo(Long codigo)throws MotOperFiscalizadorDaoException;
	public void insert(MotOperFiscalizador dto) throws MotOperFiscalizadorDaoException;
	
	
}
