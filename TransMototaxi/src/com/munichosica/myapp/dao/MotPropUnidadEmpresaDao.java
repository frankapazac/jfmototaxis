package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;

public interface MotPropUnidadEmpresaDao {

	public List<MotPropUnidadEmpresa> findByCriterio(String criterio, String texto, Long empcodigo_D) throws MotPropUnidadEmpresaDaoException;

}
