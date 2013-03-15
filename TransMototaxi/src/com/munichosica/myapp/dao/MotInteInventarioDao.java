package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotInteInventario;
import com.munichosica.myapp.exceptions.MotInteInventarioDaoException;

public interface MotInteInventarioDao {
	public void insertar(MotInteInventario inteInventario)throws MotInteInventarioDaoException;
	public List<MotInteInventario> findByInternamiento(Long codigo)throws MotInteInventarioDaoException;
	public void modificar(MotInteInventario inventario)throws MotInteInventarioDaoException;
}
