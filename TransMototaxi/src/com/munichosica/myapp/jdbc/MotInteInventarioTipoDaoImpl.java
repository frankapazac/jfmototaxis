package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInteInventarioTipoDao;
import com.munichosica.myapp.dto.MotInteInventarioTipo;
import com.munichosica.myapp.exceptions.MotInteInventarioTipoDaoException;

public class MotInteInventarioTipoDaoImpl implements MotInteInventarioTipoDao{

	@Override
	public List<MotInteInventarioTipo> findbyTipo(String tipo)
			throws MotInteInventarioTipoDaoException {
		List<MotInteInventarioTipo> list=null;
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INTEINVENTARIOTIPOBYTIPO;1(?)}");
			stmt.setString(1, tipo);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInteInventarioTipo>();
				rs=stmt.getResultSet();
				MotInteInventarioTipo inventarioTipo=null;
				while(rs.next()){
					inventarioTipo=new MotInteInventarioTipo();
					inventarioTipo.setBitcodigoI(rs.getInt("CODIGO"));
					inventarioTipo.setBitnombreV(rs.getString("NOMBRE"));
					list.add(inventarioTipo);
				}
			}
		} catch (SQLException e) {
			throw new MotInteInventarioTipoDaoException(e.getMessage(), e);
		}
		return list;
	}

}
