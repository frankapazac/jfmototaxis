package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotZonaDao;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;
import com.munichosica.myapp.exceptions.MotZonaDaoException;

public class MotZonaDaoImpl implements MotZonaDao {

	@Override
	public List<MotZona> findByCriterio(String criterio, String texto)
			throws MotZonaDaoException{

		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotZona> list=new ArrayList<MotZona>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTZONA_BYCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotZona zona=null;
				while(rs.next()){
					zona=new MotZona();
					zona.setZoncodigo_I(rs.getLong("CODIGO"));
					zona.setZonnombre_V(rs.getString("ZONA"));
					zona.setZonEstado_C(rs.getString("ESTADO"));
					list.add(zona);
				}
			}
		} catch (SQLException e) {
			throw new MotZonaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
		
	}
	
	@Override
	public void insert(MotZona dto) throws MotZonaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_ZONA;1 (?,?,?)}");
			stmt.setLong(1,dto.getZoncodigo_I());
			stmt.setString(2,dto.getZonnombre_V());
			stmt.setString(3,dto.getZonEstado_C());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotZonaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}

	}


	@Override
	public MotZona findByPrimaryKey(Long codigo) throws MotZonaDaoException {

		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotZona zona = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_MOTZONA_BYID;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					
					zona = new MotZona();
					zona.setZoncodigo_I(rs.getLong("CODIGO"));
					zona.setZonnombre_V(rs.getString("ZONA"));
					zona.setZonEstado_C(rs.getString("ESTADO"));
				}
			}
		} catch (Exception ex) {
			throw new MotZonaDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return zona;
	}

	@Override
	public List<MotZona> findAll() throws MotZonaDaoException {
			Connection conn=null;
			CallableStatement stmt=null;
			ResultSet rs=null;
			
			List<MotZona> list=new ArrayList<MotZona>();
			try {
				conn=ResourceManager.getConnection();
				stmt=conn.prepareCall("{call SP_GET_FINDALLZONASADMINISTRATIVAS;1}");
				
				boolean results=stmt.execute();
				if(results){
					rs=stmt.getResultSet();
					MotZona zona= null;
					while(rs.next()){
						zona=new MotZona();
						zona.setZoncodigo_I(rs.getLong("ZONCODIGO_I"));
						zona.setZonnombre_V(rs.getString("ZONNOMBRE_V"));
						list.add(zona);
					}
				}
			} catch (SQLException ex) {
				throw new MotZonaDaoException( "Exception: " + ex.getMessage(), ex );
			}finally{
				ResourceManager.close(rs);
				ResourceManager.close(stmt);
				ResourceManager.close(conn);
			}
			
			return list;
		
	}

	@Override
	public void delete(Long codigo) throws MotZonaDaoException{

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_DEL_MOT_ZONA;1 (?)}");
			stmt.setLong(1,codigo);
			stmt.execute();

		} catch (SQLException e) {
			throw new MotZonaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}


}
