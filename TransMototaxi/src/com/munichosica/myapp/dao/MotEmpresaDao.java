package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotEmpPropietario;
import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;

public interface MotEmpresaDao {
	public List<MotEmpresa> listar(String criterio,String texto) throws MotEmpresaDaoException;
	public void insert(MotEmpresa empresa) throws MotEmpresaDaoException;
	public MotEmpresa get(Long codigo) throws MotEmpresaDaoException;
	public void delete(Long codigo) throws MotEmpresaDaoException;
	public void actualizar(MotEmpresa empresa) throws MotEmpresaDaoException;
	public MotEmpresa findByEmpresa(Long codigo) throws MotEmpresaDaoException; 
	public void update(MotEmpresa dto) throws MotEmpresaDaoException;
	public List<MotEmpPropietario> listarPropietarios(Long empCodigoD, String criterio, String texto, String estado) throws MotEmpresaDaoException;
	public void insertarEmpPropietario(MotEmpPropietario propietario)throws MotEmpresaDaoException;
	public MotEmpPropietario findByEmpresaPropietario(Long codigo)throws MotEmpresaDaoException;
	public List<MotEmpPropietario> listarPropietariosDDL(Long empcodigoD)throws MotEmpresaDaoException;
	public boolean ExisteRepresentante(Long empcodigoD);
	public MotEmpRepresentante obtenerRepresentanteEmpresa(Long empcodigoD)throws MotEmpresaDaoException;
	public void insertarRepresentante(MotEmpRepresentante representante)throws MotEmpresaDaoException;
	public void actualizarRepresentante(MotEmpRepresentante representante)throws MotEmpresaDaoException;
}
