package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInteInventarioTipoDao;
import com.munichosica.myapp.dto.MotInteInventarioTipo;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotInteInventarioTipoDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;

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
	
	
	//
	@Override
	public List<MotInteInventarioTipo> findByCriterio(String tipo, String criterio, String texto, Integer codigo)
			throws MotInteInventarioTipoDaoException{
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotInteInventarioTipo> list=new ArrayList<MotInteInventarioTipo>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INVENTARIO_TIPOPORCRITERIO;1(?,?,?,?)}");
			stmt.setString(1, tipo);
			stmt.setString(2, criterio);
			stmt.setString(3, texto);
			stmt.setInt(4, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotInteInventarioTipo inventario=null;
				while(rs.next()){
					inventario=new MotInteInventarioTipo();
					inventario.setBitcodigoI(rs.getInt("CODIGO"));
					inventario.setBitnombreV(rs.getString("NOMBRE"));
					inventario.setBittipoC(rs.getString("TIPO"));
					inventario.setBitestadoC(rs.getString("ESTADO"));
					list.add(inventario);
				}
			}
			
		} catch (SQLException e) {
			throw new MotInteInventarioTipoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
	}
	
	
	
	@Override
	public void insert(MotInteInventarioTipo dto) throws MotInteInventarioTipoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_INTE_INVENTARIO_TIPO;1 (?,?,?,?)}");
			stmt.setInt(1,dto.getBitcodigoI());
			stmt.setString(2,dto.getBitnombreV());
			stmt.setString(3,dto.getBittipoC());
			stmt.setString(4, dto.getBitestadoC());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotInteInventarioTipoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}

	@Override
	public void delete(Integer codigo) throws MotInteInventarioTipoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_DEL_INTE_INVENTARIO_TIPO;1 (?)}");
			stmt.setInt(1,codigo);
			stmt.execute();

		} catch (SQLException e) {
			throw new MotInteInventarioTipoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}

	

	@Override
	public MotInteInventarioTipo findByid(String tipo, String criterio,
			String texto, Integer codigo)
			throws MotInteInventarioTipoDaoException {
		
		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotInteInventarioTipo inventario=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INVENTARIO_TIPOPORCRITERIO;1(?,?,?,?)}");
			stmt.setString(1, tipo);
			stmt.setString(2, criterio);
			stmt.setString(3, texto);
			stmt.setInt(4, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					inventario=new MotInteInventarioTipo();
					inventario.setBitcodigoI(rs.getInt("CODIGO"));
					inventario.setBitnombreV(rs.getString("NOMBRE"));
					inventario.setBittipoC(rs.getString("TIPO"));
					inventario.setBitestadoC(rs.getString("ESTADO"));
				}
			}
			
		} catch (SQLException e) {
			throw new MotInteInventarioTipoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return inventario;
		
	}

}
