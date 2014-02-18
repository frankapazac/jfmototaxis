package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.exceptions.MotInternamientoDaoException;

public interface MotInternamientoDao {
	public void procesar(MotInternamiento internamiento)throws MotInternamientoDaoException;
	public List<MotInternamiento> listarPorCriterio(String criterio, String texto)throws MotInternamientoDaoException;
	public MotInternamiento get(Long codigo)throws MotInternamientoDaoException;
	MotInternamiento getPropietarioInternamiento(Long codigo)
			throws MotInternamientoDaoException;
}
