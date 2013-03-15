package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotBoletaInternamiento;
import com.munichosica.myapp.exceptions.MotBoletaInternamientoDaoException;

public interface MotBoletaInternamientoDao {
	public void procesar(MotBoletaInternamiento boletaInternamiento)throws MotBoletaInternamientoDaoException;
}
