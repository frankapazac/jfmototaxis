package com.munichosica.myapp.factory;
import com.munichosica.myapp.dao.MotTipoMedidaDao;
import com.munichosica.myapp.jdbc.MotTipoMedidaDaoImpl;

public class MotTipoMedidaDaoFactory {
	
	public static MotTipoMedidaDao create(){
		return new MotTipoMedidaDaoImpl();
	}

}
