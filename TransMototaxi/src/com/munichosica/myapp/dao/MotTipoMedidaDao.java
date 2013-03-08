package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;

public interface MotTipoMedidaDao {

	public List<MotTipoMedida> findAllTipoMedida() throws MotTipoMedidaDaoException;
	
}
