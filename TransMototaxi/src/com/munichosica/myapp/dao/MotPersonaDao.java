package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotPersonaDaoException;

public interface MotPersonaDao {
	public void insertar(MotPersona persona) throws MotPersonaDaoException;
	public MotPersona obtener(Long codigo) throws MotPersonaDaoException;
	public MotPersona obtenerPorDni(String dni) throws MotPersonaDaoException;
}
