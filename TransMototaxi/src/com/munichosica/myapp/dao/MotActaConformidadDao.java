package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotActaConformidad;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotActaConformidadDaoException;

public interface MotActaConformidadDao {
	public void insertar(MotActaConformidad actaConformidad) throws MotActaConformidadDaoException;

	public List<MotPersona> obtenerPropietarioConductorByInternamiento(
			Long codigo) throws MotActaConformidadDaoException;
}
