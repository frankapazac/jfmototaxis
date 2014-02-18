package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotActaConformidadDao;
import com.munichosica.myapp.dto.MotActaConformidad;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotActaConformidadDaoException;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;

public class MotActaConformidadDaoImpl implements MotActaConformidadDao{

	@Override
	public void insertar(MotActaConformidad actaConformidad)
			throws MotActaConformidadDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_ACTA_CONFORMIDAD;1(?,?)}");
			stmt.setLong(1, actaConformidad.getAcocodigoD());
			stmt.setString(2, actaConformidad.getAcodescripcionV());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotActaConformidadDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}		
	}

	@Override
	public List<MotPersona> obtenerPropietarioConductorByInternamiento(
			Long codigo) throws MotActaConformidadDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotPersona> personas=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_ACTA_CONFORMIDAD_PERSONAS;1(?)}");
			stmt.setLong(1, codigo);
			boolean result=stmt.execute();
			if(result){
				personas=new ArrayList<MotPersona>();
				rs=stmt.getResultSet();
				MotPersona persona=null;
				while(rs.next()){
					persona=new MotPersona();
					persona.setPercodigoD(rs.getLong("CODIGO"));
					persona.setPernombresV(rs.getString("NOMBRES"));
					personas.add(persona);
				}
			}
		} catch (SQLException e) {
			throw new MotActaConformidadDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}	
		return personas;
	}
	
}
