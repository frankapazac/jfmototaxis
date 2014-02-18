package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.omg.CORBA.PERSIST_STORE;

import com.munichosica.myapp.dao.MotPersonaDao;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotPersonaDaoException;

public class MotPersonaDaiImpl implements MotPersonaDao{

	@Override
	public void insertar(MotPersona persona) throws MotPersonaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_PERSONA;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, persona.getPercodigoD());
			stmt.setString(2, persona.getPerdniV());
			stmt.setString(3, persona.getPernombresV());
			stmt.setString(4, persona.getPerpaternoV());
			stmt.setString(5, persona.getPermaternoV());
			stmt.setString(6, persona.getPernacimientoF());
			stmt.setString(7, persona.getPerestadocivilC());
			stmt.setString(8, persona.getPermovilclaV());
			stmt.setString(9, persona.getPermovilmovV());
			stmt.setString(10, persona.getPermovilnexV());
			stmt.setString(11, persona.getPerteleffijoV());
			stmt.setString(12, persona.getPeremailV());
			stmt.setString(13, persona.getPerdomicilioV());
			stmt.setString(14, persona.getPerubdptoV());
			stmt.setString(15, persona.getPerubprovV());
			stmt.setString(16, persona.getPerubidistV());
			stmt.setString(17, persona.getPersexoC());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				persona.setPercodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotPersonaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}

	@Override
	public MotPersona obtener(Long codigo) throws MotPersonaDaoException {
		MotPersona persona=null;
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PERSONA_POR_ID;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				persona=new MotPersona();
				rs=stmt.getResultSet();
				if(rs.next()){
					persona.setPercodigoD(rs.getLong("CODIGO"));
					persona.setPerdniV(rs.getString("DNI"));
					persona.setPernombresV(rs.getString("NOMBRES"));
					persona.setPerpaternoV(rs.getString("PATERNO"));
					persona.setPermaternoV(rs.getString("MATERNO"));
					persona.setPernacimientoF(rs.getString("NACIMIENTO"));
					persona.setPerestadocivilC(rs.getString("ESTADO_CIVIL"));
					persona.setPermovilclaV(rs.getString("CLARO"));
					persona.setPermovilmovV(rs.getString("MOVISTAR"));
					persona.setPermovilnexV(rs.getString("NEXTEL"));
					persona.setPerteleffijoV(rs.getString("TELEFONO_FIJO"));
					persona.setPeremailV(rs.getString("EMAIL"));
					persona.setPerdomicilioV(rs.getString("DOMICILIO"));
					persona.setPerubdptoV(rs.getString("DEPARTAMENTO"));
					persona.setPerubdptonombreV(rs.getString("DEPT_NOMBRE"));
					persona.setPerubprovV(rs.getString("PROVINCIA"));
					persona.setPerubprovnombreV(rs.getString("PROV_NOMBRE"));
					persona.setPerubidistV(rs.getString("DISTRITO"));
					persona.setPerubidistnombreV(rs.getString("DIST_NOMBRE"));
					persona.setPersexoC(rs.getString("SEXO"));
				}
			}
		} catch (SQLException e) {
			throw new MotPersonaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
		return persona;
	}

	@Override
	public MotPersona obtenerPorDni(String dni) throws MotPersonaDaoException {
		MotPersona persona=null;
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PERSONA_POR_DNI;1(?)}");
			stmt.setString(1, dni);
			boolean results=stmt.execute();
			if(results){
				persona=new MotPersona();
				rs=stmt.getResultSet();
				if(rs.next()){
					persona.setPercodigoD(rs.getLong("CODIGO"));
					persona.setPerdniV(rs.getString("DNI"));
					persona.setPernombresV(rs.getString("NOMBRES"));
					persona.setPerpaternoV(rs.getString("PATERNO"));
					persona.setPermaternoV(rs.getString("MATERNO"));
					persona.setPernacimientoF(rs.getString("NACIMIENTO"));
					persona.setPerestadocivilC(rs.getString("ESTADO_CIVIL"));
					persona.setPermovilclaV(rs.getString("CLARO"));
					persona.setPermovilmovV(rs.getString("MOVISTAR"));
					persona.setPermovilnexV(rs.getString("NEXTEL"));
					persona.setPerteleffijoV(rs.getString("TELEFONO_FIJO"));
					persona.setPeremailV(rs.getString("EMAIL"));
					persona.setPerdomicilioV(rs.getString("DOMICILIO"));
					persona.setPerubdptoV(rs.getString("DEPARTAMENTO"));
					persona.setPerubdptonombreV(rs.getString("DEPT_NOMBRE"));
					persona.setPerubprovV(rs.getString("PROVINCIA"));
					persona.setPerubprovnombreV(rs.getString("PROV_NOMBRE"));
					persona.setPerubidistV(rs.getString("DISTRITO"));
					persona.setPerubidistnombreV(rs.getString("DIST_NOMBRE"));
					persona.setPersexoC(rs.getString("SEXO"));
					//persona.setPerestadocivilC(rs.getString("ESTADO"));
				}
			}
		} catch (SQLException e) {
			throw new MotPersonaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
		return persona;
	}

}
