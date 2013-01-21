package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotUbigeoDao;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.exceptions.MotUbigeoDaoException;

public class MotUbigeoDaoImpl implements MotUbigeoDao{
	
	protected static final Logger logger = Logger.getLogger( MotUbigeoDaoImpl.class );
	
	public List<MotUbigeo> findAllDepartamentos() throws MotUbigeoDaoException{
		return findByPkValue("", (long) 2);
	}
	
	public List<MotUbigeo> findProvinciaByPrimaryKey(String idubigeo) throws MotUbigeoDaoException{
		return findByPkValue(idubigeo, (long) 4);
	}
	
	public List<MotUbigeo> findDistritoByPrimaryKey(String idubigeo) throws MotUbigeoDaoException{
		return findByPkValue(idubigeo, (long) 6);
	}
	
	@Override
	public List<MotUbigeo> findByPkValue(String idubigeo, Long value)
			throws MotUbigeoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotUbigeo> list=new ArrayList<MotUbigeo>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_GET_UBIGEO;1(?, ?)}");
			stmt.setString(1, idubigeo);
			stmt.setLong(2, value);
			
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotUbigeo ubigeo=null;
				while(rs.next()){
					ubigeo=new MotUbigeo();
					ubigeo.setIdubigeo(rs.getString("ID_UBI_GEO"));
					ubigeo.setNombubigeo(rs.getString("NOM_UBI_GEO"));
					list.add(ubigeo);
				}
			}
		} catch (SQLException ex) {
			logger.error( "Exception: " + ex.getMessage(), ex );
			throw new MotUbigeoDaoException( "Exception: " + ex.getMessage(), ex );
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}
	
}
