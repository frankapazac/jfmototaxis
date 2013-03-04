package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotPapeleta;
import com.munichosica.myapp.exceptions.MotPapeletaDaoException;

public interface MotPapeletaDao {
	public void insert(MotPapeleta papeleta) throws MotPapeletaDaoException; 
	public List<MotPapeleta> findByCriterio(String criterio, String texto) throws MotPapeletaDaoException;
}
