package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotMarcaDao;
import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.exceptions.MotMarcaDaoException;

public class MotMarcaDaoImpl implements MotMarcaDao{

	@Override
	public List<MotMarca> findAll() throws MotMarcaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotMarca> list=new ArrayList<MotMarca>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MARCAS;1}");
			boolean results=stmt.execute();
			if(results){
				MotMarca marca=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					marca=new MotMarca();
					marca.setMarcodigoI(rs.getInt("CODIGO"));
					marca.setMarnombreV(rs.getString("NOMBRE"));
					list.add(marca);
				}
			}
		} catch (SQLException e) {
			throw new MotMarcaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
