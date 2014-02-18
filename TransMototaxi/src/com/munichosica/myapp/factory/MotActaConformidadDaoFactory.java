package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotActaConformidadDao;
import com.munichosica.myapp.jdbc.MotActaConformidadDaoImpl;

public class MotActaConformidadDaoFactory {
	public static MotActaConformidadDao create(){
		return new MotActaConformidadDaoImpl();
	}
}
