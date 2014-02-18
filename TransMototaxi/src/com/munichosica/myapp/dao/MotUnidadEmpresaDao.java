package com.munichosica.myapp.dao;

import java.util.List;


import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;

public interface MotUnidadEmpresaDao {

	public void insert(MotUnidadEmpresa dto) throws MotUnidadEmpresaDaoException;
	public void update(MotUnidadEmpresa dto) throws MotUnidadEmpresaDaoException;
	public void delete(MotUnidadEmpresa dto) throws MotUnidadEmpresaDaoException;
	public MotUnidadEmpresa findByPrimaryKey(Long codigo) throws MotUnidadEmpresaDaoException;
	public MotUnidadEmpresa findByCodigo(Long codigo) throws MotUnidadEmpresaDaoException;
	public List<MotUnidadEmpresa> findByCriterio(String criterio,String texto, Long modcodigo_D)throws MotUnidadEmpresaDaoException;
	public List<MotUnidadEmpresa> findAllPlacas() throws MotUnidadEmpresaDaoException;
	public MotUnidadEmpresa findByPlaca(String placa) throws MotUnidadEmpresaDaoException;
	public List<MotUnidadEmpresa> findAllPlacasByAsociado()
			throws MotUnidadEmpresaDaoException;
	public MotUnidadEmpresa findPmoCodigo(Long codigo)
			throws MotUnidadEmpresaDaoException;
	MotUnidadEmpresa findPmoCodigoByPlaca(String placa)
			throws MotUnidadEmpresaDaoException;
	MotUnidadEmpresa findUnidadByPlaca(String placa)
			throws MotUnidadEmpresaDaoException;
	MotUnidadEmpresa findPmoCodigoByPlaca_Papeleta(String placa)
			throws MotUnidadEmpresaDaoException;
}
