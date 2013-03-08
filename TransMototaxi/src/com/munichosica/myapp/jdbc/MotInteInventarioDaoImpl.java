package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.munichosica.myapp.dao.MotInteInventarioDao;
import com.munichosica.myapp.dto.MotInteInventario;
import com.munichosica.myapp.exceptions.MotInteInventarioDaoException;

public class MotInteInventarioDaoImpl implements MotInteInventarioDao{

	@Override
	public void procesar(MotInteInventario inteInventario)
			throws MotInteInventarioDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_INTE_INVENTARIO;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, inteInventario.getBivcodigoD());
			stmt.setLong(2, inteInventario.getInternamiento().getIntcodigoD());
			stmt.setInt(3, inteInventario.getInventarioTipo().getBitcodigoI());
			stmt.setString(4, inteInventario.getBivestadoC());
			stmt.setInt(5, inteInventario.getBivcantidadI());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				inteInventario.setBivcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotInteInventarioDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

}
