package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;

public interface MotEmpresaDao {

	public MotEmpresa findByEmpresa(Long codigo) throws MotEmpresaDaoException; 
	public void update(MotEmpresa dto) throws MotEmpresaDaoException;
}
