package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotPolicia;
import com.munichosica.myapp.exceptions.MotPoliciaDaoException;

public interface MotPoliciaDao {
	public List<MotPolicia> findAll() throws MotPoliciaDaoException;
	public MotPolicia findByCarnet(String carnet) throws MotPoliciaDaoException;
	public MotPolicia findByCodigo(int codigo) throws MotPoliciaDaoException;
	public void procesar(MotPolicia policia) throws MotPoliciaDaoException;
}
