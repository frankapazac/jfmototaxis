package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.munichosica.myapp.dao.UsuEmpDao;
import com.munichosica.myapp.dto.UsuEmp;
import com.munichosica.myapp.exceptions.UsuarioDaoException;

public class UsuEmpDaoImpl implements UsuEmpDao{

	@Override
	public void insertar(UsuEmp usuEmp) throws UsuarioDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_USU_EMPR;1(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(1, usuEmp.getUsecodigoI());
			stmt.setInt(2, usuEmp.getUsuario().getUsucodigoI());
			stmt.setLong(3, usuEmp.getEmpresa().getEmpcodigoD());
			stmt.execute();
			Integer codigo=stmt.getInt(1);
			if(codigo!=null){
				usuEmp.setUsecodigoI(codigo);
			}
		} catch (SQLException e) {
			throw new UsuarioDaoException(e.getMessage(), e);
		}	finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}		
	}

}
