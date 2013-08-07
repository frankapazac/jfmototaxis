package com.munichosica.myapp.factory;

import com.munichosica.myapp.dao.MotPersonaDao;
import com.munichosica.myapp.jdbc.MotPersonaDaiImpl;

public class MotPersonaDaoFactory {
	public static MotPersonaDao create(){
		return new MotPersonaDaiImpl();
	}
}
