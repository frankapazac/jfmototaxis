package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.munichosica.myapp.dao.MotEmpParaderoDao;
import com.munichosica.myapp.dto.MotEmpParadero;
import com.munichosica.myapp.exceptions.MotEmpParaderoDaoException;

public class MotEmpParaderoDaoImpl  implements MotEmpParaderoDao{

	@Override
	public void insert(MotEmpParadero dto) throws MotEmpParaderoDaoException{

		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_TRA_INS_EMPRESA_PARADERO;1(?,?)}");
			stmt.setLong(1, dto.getEmpresa().getEmpcodigoD());
			stmt.setInt(2, dto.getParadero().getParcodigoI());
			stmt.execute();		
		} catch (SQLException e) {
			throw new MotEmpParaderoDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
				
		
	}

	@Override
	public void delete(MotEmpParadero dto) throws MotEmpParaderoDaoException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_TRA_UPD_ESTADO_EMP_PARADERO;1(?)}");
			stmt.setLong(1,  dto.getEpacodigo_I());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotEmpParaderoDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	
}
