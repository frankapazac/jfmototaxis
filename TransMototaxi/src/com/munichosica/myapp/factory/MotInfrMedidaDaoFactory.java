package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotInfrMedidaDao;
import com.munichosica.myapp.jdbc.MotInfrMedidaDaoImpl;

public class MotInfrMedidaDaoFactory {
	public static MotInfrMedidaDao create(){
		return new MotInfrMedidaDaoImpl();
	}
}
