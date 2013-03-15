package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInteInventarioTipo;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotInteInventarioTipoDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.jdbc.MotInteInventarioDaoImpl;

public interface MotInteInventarioTipoDao {
	public List<MotInteInventarioTipo> findbyTipo(String tipo)throws MotInteInventarioTipoDaoException;
	public List<MotInteInventarioTipo> findByCriterio(String tipo, String criterio,String texto, Integer codigo)throws MotInteInventarioTipoDaoException;
	public void insert(MotInteInventarioTipo dto)throws MotInteInventarioTipoDaoException;
	public MotInteInventarioTipo findByid(String tipo, String criterio,String texto, Integer codigo)throws MotInteInventarioTipoDaoException;
	public void delete(Integer codigo) throws MotInteInventarioTipoDaoException;;
	
}	