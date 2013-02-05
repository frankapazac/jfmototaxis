package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.UsuarioDao;
import com.munichosica.myapp.jdbc.UsuarioDaoImpl;

public class UsuarioDaoFactory {
		public static UsuarioDao create(){
			return new UsuarioDaoImpl();
		}
}
