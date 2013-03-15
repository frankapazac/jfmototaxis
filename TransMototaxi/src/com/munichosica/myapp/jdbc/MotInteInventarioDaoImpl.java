package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInteInventarioDao;
import com.munichosica.myapp.dto.MotInteInventario;
import com.munichosica.myapp.exceptions.MotInteInventarioDaoException;
import com.sun.java.swing.plaf.motif.resources.motif;

public class MotInteInventarioDaoImpl implements MotInteInventarioDao{

	@Override
	public void insertar(MotInteInventario inteInventario)
			throws MotInteInventarioDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_INTE_INVENTARIO;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
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

	@Override
	public List<MotInteInventario> findByInternamiento(Long codigo)
			throws MotInteInventarioDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInteInventario> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INTEINVENTARIOBYINTERNAMIENTO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInteInventario>();
				rs=stmt.getResultSet();
				MotInteInventario inventario=null;
				while(rs.next()){
					inventario=new MotInteInventario();
					inventario.getInternamiento().setIntcodigoD(rs.getLong("INTCODIGO"));
					inventario.getInventarioTipo().setBitcodigoI(rs.getInt("BITCODIGO"));
					inventario.setBivcantidadI(rs.getInt("CANTIDAD"));
					inventario.setBivestadoC(rs.getString("ESTADO"));
					list.add(inventario);
				}
			}
		} catch (SQLException e) {
			throw new MotInteInventarioDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public void modificar(MotInteInventario inteInventario)
			throws MotInteInventarioDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_UPD_INTE_INVENTARIO;1(?,?,?,?)}");
			stmt.setLong(1, inteInventario.getInternamiento().getIntcodigoD());
			stmt.setInt(2, inteInventario.getInventarioTipo().getBitcodigoI());
			stmt.setString(3, inteInventario.getBivestadoC());
			stmt.setInt(4, inteInventario.getBivcantidadI());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotInteInventarioDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

}
