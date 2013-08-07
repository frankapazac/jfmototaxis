package com.munichosica.myapp.dao;

import java.util.List;

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
}
