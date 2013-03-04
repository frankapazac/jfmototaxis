package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.exceptions.MotInfrMedidaDaoException;

public interface MotInfrMedidaDao {
	public List<MotInfrMedida> findByInfraccion(Long infcodigo) throws MotInfrMedidaDaoException;
}
