package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInfraccionDao;
import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.exceptions.MotInfraccionDaoException;

public class MotInfraccionDaoImpl implements MotInfraccionDao{

	@Override
	public List<MotInfraccion> findAll() throws MotInfraccionDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInfraccion> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALLCODINFRACCION;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInfraccion>();
				MotInfraccion infraccion=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					infraccion=new MotInfraccion();
					infraccion.setInfcodigoD(rs.getLong("CODIGO"));
					infraccion.setInfcodigoV(rs.getString("INFCODIGO"));
					list.add(infraccion);
				}
			}
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public List<MotInfraccion> findByEstado(String estado)
			throws MotInfraccionDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInfraccion> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDINFRACCIONBYESTADO;1(?)}");
			stmt.setString(1, estado);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInfraccion>();
				MotInfraccion infraccion=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					infraccion=new MotInfraccion();
					infraccion.setInfcodigoD(rs.getLong("CODIGO"));
					infraccion.setInfcodigoV(rs.getString("INFCODIGO"));
					list.add(infraccion);
				}
			}
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
