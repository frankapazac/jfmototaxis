package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;

public interface MotOperativoDao {

	public void insert(MotOperativo dto) throws MotOperativoDaoException;
	public MotOperativo findByIdOperativo(Long codigo)throws MotOperativoDaoException;
}
