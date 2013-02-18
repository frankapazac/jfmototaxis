package com.munichosica.myapp.jdbc;

import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotConductorDao;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.exceptions.MotConductorDaoException;


public class MotConductorDaoImpl implements MotConductorDao {
		
	protected static final Logger logger = Logger.getLogger( MotEmprAsociadoDaoImpl.class );
	
	@Override
	public void insert(MotConductor dto) throws MotConductorDaoException {
			
	}

	@Override
	public void update(MotConductor dto) throws MotConductorDaoException {
	}

	@Override
	public void delete(MotConductor dto) throws MotConductorDaoException {
	}

	@Override
	public MotConductor findByPrimaryKey(Long codigo)throws MotConductorDaoException {
		return null;
	}

	@Override
	public List<MotConductor> findByCriterio(String criterio, String texto,Long concodigoD) throws MotConductorDaoException {
		return null;
	}
	
	

	
}
