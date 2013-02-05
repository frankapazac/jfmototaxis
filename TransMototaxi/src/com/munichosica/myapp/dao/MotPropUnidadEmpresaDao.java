package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;

public interface MotPropUnidadEmpresaDao {
	public void insert(MotPropUnidadEmpresa dto) throws MotPropUnidadEmpresaDaoException;
	public List<MotPropUnidadEmpresa> findByCriterio(String criterio, String texto, Long empcodigo_D) throws MotPropUnidadEmpresaDaoException;
	public List<MotPropUnidadEmpresa> findByAsociado(Long asocodigoD) throws MotPropUnidadEmpresaDaoException;
	public List<MotPropUnidadEmpresa> findByAsociado(String criterio, String texto, Long asocodigoD)throws MotPropUnidadEmpresaDaoException;
	public MotPropUnidadEmpresa findByPrimaryKey(Long codigo)throws MotPropUnidadEmpresaDaoException;
}
