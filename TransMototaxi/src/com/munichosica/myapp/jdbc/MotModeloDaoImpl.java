package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotModeloDao;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.exceptions.MotModeloDaoException;

public class MotModeloDaoImpl implements MotModeloDao {

	protected static final Logger logger = Logger.getLogger( MotInfraccionDaoImpl.class );
	
	@Override
	public List<MotModelo> findByCriterio(String criterio, String texto)
			throws MotModeloDaoException {

		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotModelo> list=new ArrayList<MotModelo>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MODELO_BY_CRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotModelo modelo=null;
				while(rs.next()){
					modelo=new MotModelo();
					modelo.setModcodigo_D(rs.getInt("CODIGO"));
					modelo.setModnombre_V(rs.getString("MODELO"));
					modelo.setModestado_C(rs.getString("ESTADO"));
					list.add(modelo);
				}
			}
		} catch (SQLException e) {
			throw new MotModeloDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
		
	}
	
	
	@Override
	public MotModelo findByIdModelo(Long codigo) throws MotModeloDaoException {

		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotModelo modelo = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_MODELO_BY_ID;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					
					modelo = new MotModelo();
					modelo.setModcodigo_D(rs.getInt("CODIGO"));
					modelo.setModnombre_V(rs.getString("MODELO"));
					modelo.setModestado_C(rs.getString("ESTADO"));
				}
			}
		} catch (Exception ex) {
			logger.error("Exception :" + ex.getMessage(),ex);
			throw new MotModeloDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return modelo;
	}
	
	
	@Override
	public void insert(MotModelo dto) throws MotModeloDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_MOT_MODELO;1 (?,?,?)}");
			stmt.setInt(1,dto.getModcodigo_D());
			stmt.setString(2,dto.getModnombre_V());
			stmt.setString(3,dto.getModestado_C());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotModeloDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}
	
	@Override
	public void delete(Integer codigo) throws MotModeloDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_DEL_MODELO;1 (?)}");
			stmt.setInt(1,codigo);
			stmt.execute();

		} catch (SQLException e) {
			throw new MotModeloDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
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
