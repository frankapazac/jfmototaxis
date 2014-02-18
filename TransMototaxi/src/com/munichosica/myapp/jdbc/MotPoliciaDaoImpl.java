package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotPoliciaDao;
import com.munichosica.myapp.dto.MotPolicia;
import com.munichosica.myapp.exceptions.MotPoliciaDaoException;

public class MotPoliciaDaoImpl implements MotPoliciaDao{

	public void procesar(MotPolicia policia) throws MotPoliciaDaoException{
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_POLICIA;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(1, policia.getPolcodigoI());
			stmt.setString(2, policia.getPolcarnetidentV());
			stmt.setString(3, policia.getPolnombresV());
			stmt.setString(4, policia.getPolpaternoV());
			stmt.setString(5, policia.getPolmaternoV());
			stmt.execute();
			Integer codigo=stmt.getInt(1);
			if(codigo!=null){
				policia.setPolcodigoI(codigo);
			}
		} catch (SQLException e) {
			throw new MotPoliciaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
	
	@Override
	public List<MotPolicia> findAll() throws MotPoliciaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotPolicia> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALLPOLICIA;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotPolicia>();
				rs=stmt.getResultSet();
				MotPolicia policia=null;
				while(rs.next()){
					policia=new MotPolicia();
					policia.setPolcodigoI(rs.getInt("CODIGO"));
					policia.setPolcarnetidentV(rs.getString("CARNETNRO"));
					policia.setPolnombresV(rs.getString("NOMBRES"));
					policia.setPolpaternoV(rs.getString("PATERNO"));
					policia.setPolmaternoV(rs.getString("MATERNO"));
					list.add(policia);
				}
			}
		} catch (SQLException e) {
			throw new MotPoliciaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public MotPolicia findByCarnet(String carnet)
			throws MotPoliciaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotPolicia policia=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDPOLICIABYCARNET;1(?)}");
			stmt.setString(1, carnet);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					policia=new MotPolicia();
					policia.setPolcodigoI(rs.getInt("CODIGO"));
					policia.setPolcarnetidentV(rs.getString("CARNETNRO"));
					policia.setPolnombresV(rs.getString("NOMBRES"));
					policia.setPolpaternoV(rs.getString("PATERNO"));
					policia.setPolmaternoV(rs.getString("MATERNO"));
				}
			}
		} catch (SQLException e) {
			throw new MotPoliciaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return policia;
	}

	@Override
	public MotPolicia findByCodigo(int codigo)
			throws MotPoliciaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotPolicia policia=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDPOLICIABYCODIGO;1(?)}");
			stmt.setInt(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					policia=new MotPolicia();
					policia.setPolcodigoI(rs.getInt("CODIGO"));
					policia.setPolcarnetidentV(rs.getString("CARNETNRO"));
					policia.setPolnombresV(rs.getString("NOMBRES"));
					policia.setPolpaternoV(rs.getString("PATERNO"));
					policia.setPolmaternoV(rs.getString("MATERNO"));
				}
			}
		} catch (SQLException e) {
			throw new MotPoliciaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return policia;
	}

}
