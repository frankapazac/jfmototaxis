package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.UsuarioDaoException;

public interface UsuarioDao {

	public void update(Usuario dto) throws UsuarioDaoException;

}
