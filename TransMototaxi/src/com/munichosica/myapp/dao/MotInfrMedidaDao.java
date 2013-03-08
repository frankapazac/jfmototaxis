package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.exceptions.MotInfrMedidaDaoException;

public interface MotInfrMedidaDao {
	public List<MotInfrMedida> findByInfraccion(Long infcodigo) throws MotInfrMedidaDaoException;
	public void insert(MotInfrMedida dto) throws MotInfrMedidaDaoException;
	public void actualizaEstado(MotInfrMedida dto) throws MotInfrMedidaDaoException;
	public List<MotInfrMedida> findMedidasxInfraccion(Long codigo)throws MotInfrMedidaDaoException;
}
