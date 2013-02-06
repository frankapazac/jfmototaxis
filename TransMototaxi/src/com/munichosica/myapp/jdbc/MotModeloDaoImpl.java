package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotModeloDao;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;

public class MotModeloDaoImpl implements MotModeloDao {

	@Override
	public void insert(MotModelo dto) throws MotModeloDaoException {
	}

	@Override
	public void update(MotModelo dto) throws MotModeloDaoException {		
	}

	@Override
	public void delete(MotModelo dto) throws MotModeloDaoException {
	}

	@Override
	public MotModelo findByPrimaryKey(int codigo) throws MotModeloDaoException {
		return null;
	}

	@Override
	public List<MotModelo> findByCriterio(String criterio, String texto,
			Long modcodigo_I) throws MotParaderoDaoException {
		return null;
	}

	@Override
	public List<MotModelo> findAll() throws MotModeloDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotModelo> list=new ArrayList<MotModelo>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MODELOS;1}");
			boolean results=stmt.execute();
			if(results){
				MotModelo modelo=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					modelo=new MotModelo();
					modelo.setModcodigo_D(rs.getInt("CODIGO"));
					modelo.setModnombre_V(rs.getString("NOMBRE"));
					list.add(modelo);
				}
			}
		} catch (SQLException e) {
			throw new MotModeloDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}
	
}
