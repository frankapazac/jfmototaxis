package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotPropUnidadEmpresaDao;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;

public class MotPropUnidadEmpresaDaoImpl implements MotPropUnidadEmpresaDao{

	@Override
	public void insert(MotPropUnidadEmpresa dto)
			throws MotPropUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_PROPUNIEMPRESA;1(?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(2, dto.getUnidadempresa().getUnecodigoD());
			stmt.setLong(3, dto.getAsociado().getAsocodigoD());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setPmocodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotPropUnidadEmpresaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
	}

	@Override
	public List<MotPropUnidadEmpresa> findByCriterio(String criterio,
			String texto, Long empcodigo_D)
			throws MotPropUnidadEmpresaDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MotPropUnidadEmpresa> findByAsociado(Long asocodigoD)
			throws MotPropUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotPropUnidadEmpresa> list=null;
		try {
			list=new ArrayList<MotPropUnidadEmpresa>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_UNIDADEMPRESABYASOCIADO;1(?)}");
			stmt.setLong(1, asocodigoD);
			boolean results=stmt.execute();
			if(results){
				MotPropUnidadEmpresa propUnidadEmpresa=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					propUnidadEmpresa=new MotPropUnidadEmpresa();
					propUnidadEmpresa.getUnidadempresa().setUnecodigoD(rs.getLong("CODIGO"));
					propUnidadEmpresa.getUnidadempresa().setUneplacanroV(rs.getString("PLACA"));
					propUnidadEmpresa.getUnidadempresa().getMarca().setMarnombreV(rs.getString("MARCA"));
					list.add(propUnidadEmpresa);
				}
			}
		} catch (SQLException e) {
			throw new MotPropUnidadEmpresaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public List<MotPropUnidadEmpresa> findByAsociado(String criterio,
			String texto, Long asocodigoD)
			throws MotPropUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotPropUnidadEmpresa> list=null;
		try {
			list=new ArrayList<MotPropUnidadEmpresa>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTOTAXISPORASOCIADO;1(?,?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			stmt.setLong(3, asocodigoD);
			boolean results=stmt.execute();
			if(results){
				MotPropUnidadEmpresa unidadEmpresa=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					unidadEmpresa=new MotPropUnidadEmpresa();
					unidadEmpresa.setPmocodigoD(rs.getLong("PMOCODIGO"));
					unidadEmpresa.getUnidadempresa().setUnecodigoD(rs.getLong("UNECODIGO"));
					unidadEmpresa.getUnidadempresa().setUneplacanroV(rs.getString("PLACA"));
					unidadEmpresa.getUnidadempresa().getMarca().setMarnombreV(rs.getString("MARCA"));
					unidadEmpresa.getUnidadempresa().getModelo().setModnombre_V(rs.getString("MODELO"));
					unidadEmpresa.getUnidadempresa().setUneannoC(rs.getString("ANNO"));
					unidadEmpresa.getUnidadempresa().setUnecolorV(rs.getString("COLOR"));
					list.add(unidadEmpresa);
				}
			}
		} catch (SQLException e) {
			throw new MotPropUnidadEmpresaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public MotPropUnidadEmpresa findByPrimaryKey(Long codigo)
			throws MotPropUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotPropUnidadEmpresa propUnidadEmpresa=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTPROPUNIDEMPRESA;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				propUnidadEmpresa=new MotPropUnidadEmpresa();
				rs=stmt.getResultSet();
				if(rs.next()){
					propUnidadEmpresa.setPmocodigoD(rs.getLong("PMOCODIGO"));
					propUnidadEmpresa.getAsociado().setAsocodigoD(rs.getLong("ASOCODIGO"));
					propUnidadEmpresa.getAsociado().getPersona().setPernombresV(rs.getString("NOMBRES"));
					propUnidadEmpresa.getAsociado().getPersona().setPerpaternoV(rs.getString("PATERNO"));
					propUnidadEmpresa.getAsociado().getPersona().setPermaternoV(rs.getString("MATERNO"));
					propUnidadEmpresa.getAsociado().getPersona().setPerdniV(rs.getString("DNI"));
					propUnidadEmpresa.getUnidadempresa().setUneplacanroV(rs.getString("PLACA"));
					propUnidadEmpresa.getUnidadempresa().getMarca().setMarnombreV(rs.getString("MARCA"));
					propUnidadEmpresa.getUnidadempresa().getModelo().setModnombre_V(rs.getString("MODELO"));
					propUnidadEmpresa.getUnidadempresa().setUneannoC(rs.getString("ANNO"));
					propUnidadEmpresa.getUnidadempresa().setUnecolorV(rs.getString("COLOR"));
				}
			}
		} catch (SQLException e) {
			throw new MotPropUnidadEmpresaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return propUnidadEmpresa;
	}

}
