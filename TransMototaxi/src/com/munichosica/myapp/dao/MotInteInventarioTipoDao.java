package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInteInventarioTipo;
import com.munichosica.myapp.exceptions.MotInteInventarioTipoDaoException;

public interface MotInteInventarioTipoDao {
	public List<MotInteInventarioTipo> findbyTipo(String tipo)throws MotInteInventarioTipoDaoException;
}
