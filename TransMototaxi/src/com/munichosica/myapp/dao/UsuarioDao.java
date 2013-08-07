package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.UsuarioDaoException;

public interface UsuarioDao {
	public void insertar(Usuario usuario) throws UsuarioDaoException;
	public void update(Usuario dto) throws UsuarioDaoException;
	public Usuario obtenerUsuarioEmpresa(Long codigo) throws UsuarioDaoException;
}
