package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.UsuEmp;
import com.munichosica.myapp.exceptions.UsuarioDaoException;

public interface UsuEmpDao {
	public void insertar(UsuEmp usuEmp) throws UsuarioDaoException;
}
