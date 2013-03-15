package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotMarcaDao;
import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.exceptions.MotMarcaDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;

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
	

	@Override
	public List<MotMarca> findByCriterio(String criterio, String texto)
			throws MotMarcaDaoException {

		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotMarca> list=new ArrayList<MotMarca>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MARCA_BY_CRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotMarca marca=null;
				while(rs.next()){
					marca=new MotMarca();
					marca.setMarcodigoI(rs.getInt("CODIGO"));
					marca.setMarnombreV(rs.getString("MARCA"));
					marca.setMarestadoC(rs.getString("ESTADO"));
					list.add(marca);
				}
			}
		} catch (SQLException e) {
			throw new MotMarcaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
		
	}
	
	
	@Override
	public MotMarca findByIdMarca(Long codigo) throws MotMarcaDaoException {

		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotMarca marca = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_MARCA_BY_ID;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					
					marca = new MotMarca();
					marca.setMarcodigoI(rs.getInt("CODIGO"));
					marca.setMarnombreV(rs.getString("MARCA"));
					marca.setMarestadoC(rs.getString("ESTADO"));
				}
			}
		} catch (Exception ex) {
			throw new MotMarcaDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return marca;
	}
	
	
	@Override
	public void insert(MotMarca dto) throws MotMarcaDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_MOT_MARCA;1 (?,?,?)}");
			stmt.setInt(1,dto.getMarcodigoI());
			stmt.setString(2,dto.getMarnombreV());
			stmt.setString(3,dto.getMarestadoC());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotMarcaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}
	
	@Override
	public void delete(Integer codigo) throws MotMarcaDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_DEL_MARCA;1 (?)}");
			stmt.setInt(1,codigo);
			stmt.execute();

		} catch (SQLException e) {
			throw new MotMarcaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}


}
