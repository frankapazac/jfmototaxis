package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotZonaDao;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotZonaDaoException;

public class MotZonaDaoImpl implements MotZonaDao {

	@Override
	public void insert(MotZonaDao dto) throws MotZonaDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MotZonaDao dto) throws MotZonaDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(MotZonaDao dto) throws MotZonaDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public MotZonaDao findByPrimaryKey(Long codigo) throws MotZonaDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MotZona> findAll() throws MotZonaDaoException {
			Connection conn=null;
			CallableStatement stmt=null;
			ResultSet rs=null;
			
			List<MotZona> list=new ArrayList<MotZona>();
			try {
				conn=ResourceManager.getConnection();
				stmt=conn.prepareCall("{call SP_GET_FINDALLZONASADMINISTRATIVAS;1}");
				
				boolean results=stmt.execute();
				if(results){
					rs=stmt.getResultSet();
					MotZona zona= null;
					while(rs.next()){
						zona=new MotZona();
						zona.setZoncodigo_I(rs.getLong("ZONCODIGO_I"));
						zona.setZonnombre_V(rs.getString("ZONNOMBRE_V"));
						list.add(zona);
					}
				}
			} catch (SQLException ex) {
				throw new MotZonaDaoException( "Exception: " + ex.getMessage(), ex );
			}finally{
				ResourceManager.close(rs);
				ResourceManager.close(stmt);
				ResourceManager.close(conn);
			}
			
			return list;
		
	}

}
