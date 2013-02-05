
package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.exceptions.MotEmpRepresentanteDaoException;



public interface MotEmpRepresentanteDao {

	public MotEmpRepresentante findByEmpresa(long codigo) throws MotEmpRepresentanteDaoException;
	
}
