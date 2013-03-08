package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotInteInventario;
import com.munichosica.myapp.exceptions.MotInteInventarioDaoException;

public interface MotInteInventarioDao {
	public void procesar(MotInteInventario inteInventario)throws MotInteInventarioDaoException;
}
